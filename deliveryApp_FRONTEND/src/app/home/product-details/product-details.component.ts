import { Component } from '@angular/core';
import { ProductService } from 'src/app/_services/product.service';
import { OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css'],
})
export class ProductDetailsComponent implements OnInit {
  isDataLoaded: boolean = false;
  constructor(
    private productService: ProductService,
    private route: ActivatedRoute,
    private router: Router
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
        this.isDataLoaded = true;
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
        this.router.navigate(['/cart']);
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
