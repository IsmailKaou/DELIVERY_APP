import { Component } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-delete-dialog',
  templateUrl: './delete-dialog.component.html',
  styleUrls: ['./delete-dialog.component.css']
})
export class DeleteDialogComponent {
  static productId:number

  constructor(private productService: ProductService,private dialogRefClose: MatDialogRef<DeleteDialogComponent>) {

  }
  deleteProduct(){

    this.productService.deleteProduct(DeleteDialogComponent.productId).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }


  onClose(){
    this.dialogRefClose.close();
  }
}
