import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent {
  productId:number
  form:FormGroup
  selectedFile: File | null = null;
  static productId:number

  submit(form:FormGroup){

  }
  constructor(private productService: ProductService,private http: HttpClient,private dialogRef:MatDialogRef<UpdateProductComponent>) {

  }
  updateProduct(){

    this.productService.updateProduct(this.productId).subscribe(
      (response) => {
        console.log(response);
      },
      (error) => {
        console.log(error);
      }
    );
  }
  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    console.log("file",this.selectedFile)
    this.uploadFile()
  }

  uploadFile() {

    if (this.selectedFile) {
      const formData: FormData = new FormData();
      formData.append('file', this.selectedFile, this.selectedFile.name);

      this.http.post('http://localhost:8080/api/uploadFile', formData).subscribe(
        response => {
          console.log('File uploaded successfully',response);
        },
        error => {
          console.error('Error uploading file:', error);
        }
      );
    }
  }
  onCancel() {
    // close the dialog without passing any data
    this.dialogRef.close();
  }
}
