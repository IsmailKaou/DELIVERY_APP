import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  constructor(private http: HttpClient) {}

  api = 'https://deliveryapp-production-4114.up.railway.app';
  cartDetails: any[] = [];
  public emptyCart = true;
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });
  listProducts(): Observable<any> {
    return this.http.get(this.api + '/products/getProducts', {
      headers: this.requestHeader,
    });
  }
  addProduct(product) {
    return this.http.post(this.api + '/admin/products', product);
  }
  getProduct(productId) {
    return this.http.get(
      this.api + '/products/getProducts/product/' + productId,
      {
        headers: this.requestHeader,
      }
    );
  }
  deleteProduct(productId) {
    return this.http.delete(this.api + '/admin/products/' + productId);
  }

  updateProduct(productId, product) {
    return this.http.put(this.api + '/admin/products/' + productId, product);
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
    this.emptyCart = false;
    return this.http.get(this.api + '/getCartDetails');
  }
}
