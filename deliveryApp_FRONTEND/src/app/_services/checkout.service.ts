import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CheckoutService {
  constructor(private http: HttpClient) {}
  api = 'http://localhost:2000/orders';
  processPayment(paymentData): Observable<any> {
    console.log(paymentData);
    return this.http.post(this.api + '/checkout', paymentData);
  }
}
