import { Injectable } from '@angular/core';
import {
  HttpClient, HttpHeaders, HttpErrorResponse
} from "@angular/common/http";
import { Router } from '@angular/router';
import { JwtHelper, tokenNotExpired } from "angular2-jwt";
import { LoginUser } from '../models/login-user';
import { AlertifyService } from './alertify.service';
import { Observable } from 'rxjs';
import { Kullanicilar } from '../models/kullanicilar';
import "rxjs/add/operator/catch";
@Injectable({
  providedIn: 'root'
})
export class KullaniciService {

  constructor(
    private httpClient: HttpClient,
    private router: Router,
    private alertifyService: AlertifyService

  ) { }
  path: string = "http://localhost:8088/api/token";
  jwtHelper: JwtHelper = new JwtHelper();

  login(loginUser: LoginUser) {
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json");
    this.httpClient.post(this.path + "/giris", loginUser, { headers: headers }).subscribe(
      data => {
        this.saveToken(data["token"]);
        this.alertifyService.success("Sisteme başarılı bir şekilde giriş yapıldı.");
        this.router.navigateByUrl("/anasayfa");
        this.alertifyService.success("Hoşgeldin"+data["kullaniciAdi"]);
      },
      error => {
        this.alertifyService.error(" errorrr var");
      }
    );

  }
     errorHandler(error: HttpErrorResponse) {
       return Observable.throw(error.error);
    }

     register(registerUser: Kullanicilar) {
      
       let headers = new HttpHeaders();
       headers = headers.append("Content-Type", "application/json");
       this.httpClient.post(this.path + "/misafir", registerUser, { headers: headers }).subscribe(
        data => {
          debugger;
          if(data===false){
            this.alertifyService.error("Böyle Bir kullanıcı zaten var.");
            return;
          }
          this.alertifyService.success("Kayıt  başarılı bir şekilde  yapıldı. Lütfen Giriş Yapınız");
          this.router.navigateByUrl("/login");
        },
        error => {
          this.alertifyService.error("Hata");
        }
      );
        
        }

     editKullaniciLogin(kullanici) {
      return this.httpClient
         .put(this.path + "editKullanici", kullanici)
         .catch(this.errorHandler);

     }

     findKullanici(kullaniciId): Observable<Kullanicilar> {
       return this.httpClient
         .get<Kullanicilar>("http://localhost:8088/" + "kullanicilar/" + kullaniciId)
         .catch(this.errorHandler);
     }

  findKullanicibyUsername(username): Observable<any> {
    return this.httpClient
      .get<any>("http://localhost:8088/" + "kullanicilar/username/" + username)
      .catch(this.errorHandler);
  }

  saveToken(token) {
    localStorage.setItem("token", token);

    localStorage.setItem(
      "kullaniciId",
      this.jwtHelper.decodeToken(token).nameid
    );
   
    localStorage.setItem(
      "kullaniciAdi",
      this.jwtHelper.decodeToken(token).unique_name
    );
    localStorage.setItem(
      "kullaniciId",
      this.jwtHelper.decodeToken(token).role
    );
    localStorage.setItem(
      "durum",
      this.jwtHelper.decodeToken(token).given_name
    );
  }


  getToken() {
    return localStorage.getItem("token");
  }

  get token() {
    return localStorage.getItem("token");
  }





}
