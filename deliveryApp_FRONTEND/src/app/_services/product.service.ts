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
  addProduct(product){
    const token='eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBhZG1pbi5jb20iLCJpYXQiOjE2ODYyMTAwNDMsImV4cCI6MTY4NjIxMTQ4M30.-LD5mxw_jy98SwBBflu4A2xnxdWKTXr_Naw0Eya9pi4'
   // const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);

    return this.http.post(
      this.api + '/admin/products' ,product

    );
  }
  getProduct(productId) {
    return this.http.get(
      this.api + '/products/getProducts/product/' + productId,
      {
        headers: this.requestHeader,
      }
    );
  }
  deleteProduct(productId){
    return this.http.delete(
      this.api + '/admin/products/' + productId,
  
    );
  }
  updateProduct(productId){
    return this.http.put(
      this.api + '/admin/products/' + productId,
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
