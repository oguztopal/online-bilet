import { Injectable } from '@angular/core';

import { Router } from '@angular/router';
import { AlertifyService } from './alertify.service';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators/catchError';
import { throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UcusseferleriService {
  constructor(

    private httpClient: HttpClient,
    private alertifyService: AlertifyService
  ) { }

  path1: string = "http://localhost:8088/ucusseferleri";

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type' : 'application/json'
    })
  };

  ucusseferleriGetir(path: string, params: HttpParams = new HttpParams()): Observable<any> {
    return this.httpClient.get(environment.API_BASE_PATH +"ucusseferleri/ucuslar1", {params}).pipe(catchError(this.errorHandler));
  }

  kuponsorgula(kupon) {
    return this.httpClient
      .put(environment.API_BASE_PATH+"ucusseferleri/kuponsorgula/" + kupon,this.httpOptions)
      .catch(this.errorHandler);
  }

  private formatError(error: any) {
    return Observable.bind(error.error);
  }
  errorHandler(error: HttpErrorResponse) {
    return throwError(error)
  }



  getAll(): Observable<any[]> {
    return this.httpClient
      .get<any[]>(environment.API_BASE_PATH +"ucusseferleri/hepsinigetir")
      .catch(this.errorHandler);
  }

  delete(seferId: number) {
    return this.httpClient
      .put(environment.API_BASE_PATH +"ucusseferleri/delete/" + seferId,this.httpOptions)
      .catch(this.errorHandler);
  }

  add(sefer) {
    return this.httpClient
      .post(environment.API_BASE_PATH +"ucusseferleri/create", sefer)
      .catch(this.errorHandler);
  }

  edit(sefer) {
    return this.httpClient
      .put(environment.API_BASE_PATH +"ucusseferleri/edit", sefer)
      .catch(this.errorHandler);
  }

  find(seferId): Observable<any> {
    return this.httpClient
      .get<any>(environment.API_BASE_PATH +"ucusseferleri/find/" + seferId)
      .catch(this.errorHandler);
  }
  getsirketler(): Observable<any[]> {
    return this.httpClient
      .get<any[]>(environment.API_BASE_PATH +"sirketler/all")
      .catch(this.errorHandler);
  }
  getEnumSeferDurumlari() {
    let durumlar: any[] = [
      {
        durum: "UCAK_KALKMADI"
      },
      {
        durum: "GUVENLIK_KONTROLU"
      },
      {
        durum: "KALKIS_ICIN_HAZIR"
      },
      {
        durum: "ROTAR"
      },
      {
        durum: "UCUS_GERCEKLESIYOR"
      },
      {
        name: "UCUS_GERCEKLESTI"
      }
    ];
    return durumlar;
  }
}



