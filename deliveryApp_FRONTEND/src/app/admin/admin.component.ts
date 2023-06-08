import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';
import { ProductService } from '../_services/product.service';
import { MatDialog } from '@angular/material/dialog';
import { AddProductComponent } from './add-product/add-product.component';
import { DeleteDialogComponent } from './delete-dialog/delete-dialog.component';
import { UpdateProductComponent } from './update-product/update-product.component';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  message: string;
  products: any[] = [];
  currentPage: number = 1;
  itemsPerPage: number = 5;

  constructor(
    private userService: UserService,
    private productService: ProductService,
    private dialogRef: MatDialog
  ) {}

  ngOnInit(): void {
    this.forAdmin();
    this.getProducts();
  }

  forAdmin() {
    this.userService.forAdmin().subscribe(
      (res) => {
        console.log('im here');
        console.log(res);
        this.message = res;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  getProducts() {
    this.productService.listProducts().subscribe(
      (products) => {
        console.log(products);
        this.products = products;
      },
      (error) => {
        console.log(error);
      }
    );
  }

  openDialog(): void {
    this.dialogRef.open(AddProductComponent, {
      width: '60%',
    });
  }

  openDeleteDialog(id: number) {
    DeleteDialogComponent.productId = id;
    this.dialogRef.open(DeleteDialogComponent, {
      width: '60%',
    });
  }

  openUpdateDialog(product) {
    UpdateProductComponent.productId = product.id;
    UpdateProductComponent.product = product;
    this.dialogRef.open(UpdateProductComponent, {
      width: '60%',
    });
  }

  getPageProducts(): any[] {
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    return this.products.slice(startIndex, endIndex);
  }

  onPageChange(pageNumber: number) {
    this.currentPage = pageNumber;
  }
}
