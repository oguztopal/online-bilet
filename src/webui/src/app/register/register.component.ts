import { Component, OnInit } from "@angular/core";

import { AlertifyService } from "../services/alertify.service";
import {
  FormBuilder,
  FormGroup,
  Validators,
  FormControl
} from "@angular/forms";
import { Router } from "@angular/router";
import { KullaniciService } from '../services/kullanici.service';

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {
  constructor(
    private router: Router,
    private alertifyService: AlertifyService,
    private formBuilder: FormBuilder,
    private kullaniciService: KullaniciService
  ) {}

  registerForm: FormGroup;
  registerUser: any = {};
  submitted = false;

  ngOnInit() {
    this.createRegisterForm();
  }

  createRegisterForm() {
    this.registerForm = this.formBuilder.group({
      adSoyad: ["", Validators.required],
      kullaniciAdi: ["", Validators.required],
      email: ["", [Validators.required, Validators.email]],
      adres: ["", Validators.required],
      telefon: ["", Validators.required],
      sifre: ["",[Validators.required, Validators.minLength(5)]
      ]
    });
  }

  get f() { return this.registerForm.controls; }

  register() {
    this.submitted=true;
    if (this.registerForm.valid) {
      this.registerUser = Object.assign({}, this.registerForm.value);
      this.kullaniciService.register(this.registerUser);
    }
  }
}
