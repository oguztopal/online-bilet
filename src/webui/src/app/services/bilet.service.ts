import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { AlertifyService } from './alertify.service';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { catchError } from 'rxjs/operators/catchError';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { Bilet } from '../models/bilet';

@Injectable({
  providedIn: 'root'
})
export class BiletService {

  constructor(private httpClient: HttpClient) { }


  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };
  private biletOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'responseType': 'text' as 'json'
    }),

  };

  post(path: string,data ): Observable<any> {
    return this.httpClient.post(environment.API_BASE_PATH +"bilet"+ path,data, this.httpOptions).pipe(catchError(this.errorHandler));
  }

  onr(firmaId) {
    return this.httpClient
      .get("http://localhost:8088/bilet/pnrsorgula/" + firmaId)
      .catch(this.errorHandler);
  }
  biletiptal(biletId) {
    return this.httpClient
      .put(environment.API_BASE_PATH+"bilet/biletiptal/"+ biletId,this.biletOptions)
      .catch(this.errorHandler);
  }

  errorHandler(error: HttpErrorResponse) {
    return throwError(error)
  }

  private formatError(error: any) {
    return Observable.bind(error.error);
  }
  
}
