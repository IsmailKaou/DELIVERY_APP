import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}
  api = 'http://localhost:8080';

  addToCart(productId) {
    return this.http.get(this.api + '/addToCart/' + productId);
  }
}
