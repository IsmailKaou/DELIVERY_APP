import { Component } from '@angular/core';
import { ProductService } from 'src/app/_services/product.service';
import { OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css'],
})
export class ProductDetailsComponent implements OnInit {
  constructor(
    private productService: ProductService,
    private route: ActivatedRoute
  ) {}
  ngOnInit(): void {
    const productId = this.route.snapshot.paramMap.get('id');
    this.fetchDetails(productId);
  }

  product: any;
  fetchDetails(productId) {
    this.productService.getProduct(productId).subscribe(
      (product) => {
        this.product = product;
        console.log(product);
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
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
