import { Injectable } from '@angular/core';
import { Havalimanlari } from '../models/havalimanlari';
import { HttpHeaders, HttpErrorResponse, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AlertifyService } from './alertify.service';
import { getAllDebugNodes } from '@angular/core/src/debug/debug_node';

@Injectable({
  providedIn: 'root'
})
export class HavalimanlariService {
  path: string = "http://localhost:8088/havalimanlari";

  constructor(private httpClient: HttpClient , private alertifyService : AlertifyService) { }

  errorHandler(error: HttpErrorResponse) {
    return Observable.throw(error.error);
  }

  getAll(): Observable<Havalimanlari[]> {
    let headers = new HttpHeaders();
    headers = headers.append("Content-Type", "application/json");
    return this.httpClient
      .get<Havalimanlari[]>(this.path + "/all");
  }
}