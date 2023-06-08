import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.css'],
})
export class DeleteDialogComponent {
  static productId: number;

  constructor(
    private productService: ProductService,
    private dialogRefClose: MatDialogRef<DeleteDialogComponent>,
    private router: Router
  ) {}
  deleteProduct() {
    this.productService
      .deleteProduct(DeleteDialogComponent.productId)
      .subscribe(
        (response) => {
          console.log(response);
          this.dialogRefClose.close();
          this.router.navigate(['/admin']);
          window.location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
  }

  onClose() {
    this.dialogRefClose.close();
  }
}
