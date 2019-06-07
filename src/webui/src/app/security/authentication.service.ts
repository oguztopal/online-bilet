import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {map} from 'rxjs/operators';
import {environment} from "../../environments/environment";
import { AlertifyService } from '../services/alertify.service';
import { Router } from '@angular/router';

@Injectable({providedIn: 'root'})
export class AuthenticationService {
  constructor(private http: HttpClient,private router:Router, 
    private alert:AlertifyService) {
  }

  login(kullaniciAdi: string, sifre: string) {
    return this.http.post<any>( environment.API_BASE_PATH + 'api/token/giris', {kullaniciAdi, sifre})
      .pipe(map(user => {
        if (user && user.token) {
          localStorage.setItem('currentUser', JSON.stringify(user));
          localStorage.setItem("token",user.token);
          this.alert.success(JSON.parse(localStorage.getItem('currentUser')).kullaniciAdi+" Hoşgeldiniz");
        }
        return user;
      }));
  }

  register(registerData) {
    return this.http.post<any>( environment.API_BASE_PATH + 'api/token/misafir', registerData)
      .pipe(map(resp => {
        return resp;
      }));
  }

  logout() {
    localStorage.removeItem('currentUser');
    localStorage.clear();
    //this.router.navigateByUrl("/anasayfa")
  }
}