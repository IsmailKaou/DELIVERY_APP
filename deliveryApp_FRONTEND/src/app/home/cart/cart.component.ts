import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css'],
})
export class CartComponent implements OnInit {
  cartDetails: any[] = [];
  totalCart: number = 0;
  isDataLoaded: boolean = false;
  constructor(private productService: ProductService) {}
  ngOnInit(): void {
    this.getCartDetails();
  }
  isCloudImg(imageURL: string): boolean {
    // Assuming your local image paths have a specific prefix, e.g., 'local:'
    return imageURL.startsWith('http');
  }
  getCartDetails() {
    this.productService.getCartDetails().subscribe(
      (res: any) => {
        console.log(res);
        // console.log(res[0].quantity);

        this.cartDetails = res;
        this.totalCart = 0;
        for (let index = 0; index < res.length; index++) {
          this.totalCart += res[index].quantity * res[index].product.price;
        }
        this.isDataLoaded = true;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  addToCart(productId) {
    this.productService.addToCart(productId).subscribe(
      (res) => {
        console.log(res);
        this.getCartDetails();
      },
      (err) => {
        console.log(err);
      }
    );
  }
  removeFromCart(productId) {
    this.productService.removeFromCart(productId).subscribe(
      (res) => {
        console.log(res);
        this.getCartDetails();
      },
      (err) => {
        console.log(err);
      }
    );
  }
  deleteProductFromCart(productId) {
    this.productService.deleteProductFromCart(productId).subscribe(
      (res) => {
        console.log(res);
        this.getCartDetails();
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
