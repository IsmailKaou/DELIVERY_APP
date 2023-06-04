import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../model/Order';

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  constructor(private http: HttpClient) {}
  api = 'http://localhost:8080/orders';
  getOrder(orderId: string): Observable<Order> {
    return this.http.get<Order>(this.api + '/' + orderId);
  }
  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.api);
  }
}
