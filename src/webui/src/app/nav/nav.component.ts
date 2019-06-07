import { Component, OnInit } from '@angular/core';
import { BiletService } from '../services/bilet.service';
import { Router } from '@angular/router';
import { AlertifyService } from '../services/alertify.service';
import { AuthenticationService } from '../security/authentication.service';
declare let alertify: any;
@Component({
  selector: 'app-nav',
  templateUrl: './nav.component.html',
  styleUrls: ['./nav.component.css']
})
export class NavComponent implements OnInit {

  constructor(private biletService: BiletService,
    private router: Router,
    private alertifyService: AlertifyService, private authService: AuthenticationService) { }
  aktifKullanici: any;
  token:any;
  ngOnInit() {
    this.aktifKullanici = JSON.parse(localStorage.getItem('currentUser')).kullaniciAdi;

  }
  pnrSorgula() {

    alertify.prompt("Lütfen PNR kodunu giriniz", " ", (e, value) => {
      this.biletService.onr(value.replace(/\s/g, "")).subscribe(data => {
        let temp: any = data;
        this.router.navigate(['/biletbilgileri'], { queryParams: { data: JSON.stringify(temp) } })
        console.log(data);
      }, error => {
        this.alertifyService.error(error.error.message);
      });
    }
    ).setHeader('<em> Bilgi </em> ');
  }


  IsLoggedin() {
     this.token = localStorage.getItem("token");
    if (this.token != null && this.token != "") {
      return true
    }
    return false
  }

  cikisYap() {
    alert("fghjkl")
    this.token=""
    this.authService.logout();

  }
}
