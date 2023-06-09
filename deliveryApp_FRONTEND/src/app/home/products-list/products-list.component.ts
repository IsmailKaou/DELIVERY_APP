import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css'],
})
export class ProductsListComponent implements OnInit {
  constructor(private productService: ProductService) {}
  ngOnInit() {
    this.listAllProducts();
  }
  products: any[] = [];
  isCloudImg(imageURL: string): boolean {
    // Assuming your local image paths have a specific prefix, e.g., 'local:'
    return imageURL.startsWith('http');
  }

  listAllProducts() {
    this.productService.listProducts().subscribe(
      (products) => {
        console.log('heee');
        console.log(products);
        this.products = products;
      },
      (error) => {
        console.log(error);
      }
    );
  }
}
