import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}
  api = 'http://localhost:2019';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  listProducts(): Observable<any> {
    return this.http.get(this.api + '/products/getProducts', {
      headers: this.requestHeader,
    });
  }
  getProduct(productId) {
    return this.http.get(
      this.api + '/products/getProducts/product/' + productId,
      {
        headers: this.requestHeader,
      }
    );
  }
  addToCart(productId) {
    return this.http.get(this.api + '/addToCart/' + productId);
  }
}
