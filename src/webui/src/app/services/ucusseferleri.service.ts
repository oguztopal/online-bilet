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
  // getAll(): Observable<Havalimanlari[]> {
  //   let headers = new HttpHeaders();
  //   headers = headers.append("Content-Type", "application/json");
  //   return this.httpClient
  //     .get<Havalimanlari[]>(this.path + "/all");
  // }
  private formatError(error: any) {
    return Observable.bind(error.error);
  }
  errorHandler(error: HttpErrorResponse) {
    return throwError(error)
  }
}
