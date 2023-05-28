import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  constructor(private http: HttpClient) {}
  api = 'http://localhost:8080/orders';
  getOrder(orderId: string): Observable<any> {
    return this.http.get(this.api + '/' + orderId);
  }
  getOrders(): Observable<any> {
    return this.http.get(this.api);
  }
}
