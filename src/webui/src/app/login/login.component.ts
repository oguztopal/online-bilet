// import { Component, OnInit } from "@angular/core";
// import { AlertifyService } from "../services/alertify.service";
// import {
//   FormGroup,
//   FormControl,
//   Validators,
//   FormBuilder
// } from "@angular/forms";
// import { KullaniciService } from '../services/kullanici.service';
// import { Router } from '@angular/router';


// @Component({
//   selector: "app-login",
//   templateUrl: "./login.component.html",
//   styleUrls: ["./login.component.css"],
  
// })
// export class LoginComponent implements OnInit {
//   constructor(
//     private router:Router,
//     private formBuilder: FormBuilder,
//     private kullaniciService:KullaniciService
//   ) {}

//   loginUser: any = {};
//   isLogin=true

//   ngOnInit() {}
//   login() {
   
//     this.kullaniciService.login(this.loginUser);
//     this.isLogin=false;
//   }
 
//   get isAuthenticated() {
//     return this.kullaniciService.loggedIn();
//   }
//   registeraGit(){
//     this.router.navigateByUrl("register");
//   }
// }
import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {FormBuilder, FormGroup, Validators,FormControl} from '@angular/forms';
import {first} from 'rxjs/operators';
import {AuthenticationService} from "../security/authentication.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor(private formBuilder: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      kullaniciAdi: ['', Validators.required],
      sifre: ['', Validators.required]
    });

    // reset login status
    this.authenticationService.logout();

    // get return url from route parameters or default to '/'
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() {
    return this.loginForm.controls;
  }

  login() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.loginForm.invalid) {
      return;
    }

    this.loading = true;
    this.authenticationService.login(this.f.kullaniciAdi.value, this.f.sifre.value)
      .pipe(first())
      .subscribe(
        data => {
          this.router.navigate([this.returnUrl]);
        },
        error => {
          this.error = error;
          this.loading = false;
        });
  }

  registeraGit(){
         this.router.navigateByUrl("register");
       }
}