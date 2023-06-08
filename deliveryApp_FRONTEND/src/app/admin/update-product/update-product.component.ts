import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/_services/product.service';

@Component({
  selector: 'update-add-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css'],
})
export class UpdateProductComponent {
  form: FormGroup;
  selectedFile: File | null = null;
  static productId: number;
  static product;

  submit(form: FormGroup) {}
  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private http: HttpClient,
    private dialogRef: MatDialogRef<UpdateProductComponent>,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.form = new FormGroup({
      productName: new FormControl(
        UpdateProductComponent.product.name,
        Validators.required
      ),
      productPrice: new FormControl(
        UpdateProductComponent.product.price,
        Validators.required
      ),
      productImage: new FormControl(
        UpdateProductComponent.product.imageURL,
        Validators.required
      ),
      productDescription: new FormControl(
        UpdateProductComponent.product.description,
        Validators.required
      ),
    });
  }
  updateProduct() {
    const productName = this.form.get('productName').value;
    const productImage = this.form.get('productImage').value;
    //const productCategory = this.form.get('productCategory').value;
    const productDescription = this.form.get('productDescription').value;
    const productPrice = this.form.get('productPrice').value;
    console.log(productName + 'is the name');
    const product = {
      name: productName,
      imageURL: productImage,
      category: 1,
      price: productPrice,
      description: productDescription,
    };
    console.log('Category 1');
    this.productService
      .updateProduct(UpdateProductComponent.productId, product)
      .subscribe(
        (response) => {
          console.log(response);
          this.dialogRef.close();
          window.location.reload();
        },
        (error) => {
          console.log(error);
        }
      );
  }

  onCancel() {
    // close the dialog without passing any data
    this.dialogRef.close();
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
    console.log('file', this.selectedFile);
    this.uploadFile();
  }

  uploadFile() {
    if (this.selectedFile) {
      const formData: FormData = new FormData();
      formData.append('file', this.selectedFile, this.selectedFile.name);

      this.http
        .post('http://localhost:8080/api/uploadFile', formData)
        .subscribe(
          (response) => {
            console.log('File uploaded successfully', response);
          },
          (error) => {
            console.error('Error uploading file:', error);
          }
        );
    }
  }
}
