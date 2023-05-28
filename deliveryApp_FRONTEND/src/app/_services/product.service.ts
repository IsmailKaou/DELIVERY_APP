import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}
  api = 'http://localhost:8080';
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
  removeFromCart(productId) {
    return this.http.get(this.api + '/removeFromCart/' + productId);
  }
  deleteProductFromCart(productId) {
    return this.http.delete(this.api + '/deleteCartItem/' + productId);
  }

  getCartDetails() {
    return this.http.get(this.api + '/getCartDetails');
  }
}
