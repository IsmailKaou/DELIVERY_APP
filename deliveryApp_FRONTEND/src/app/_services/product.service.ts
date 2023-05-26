import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}
  api = 'http://localhost:2019/';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  public listAllProduct(): Observable<any> {
    console.log('Fetching data..');
    return this.http.get(this.api + 'products/', {
      headers: this.requestHeader,
    });
  }
}
