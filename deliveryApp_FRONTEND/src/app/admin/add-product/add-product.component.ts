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
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css'],
})
export class AddProductComponent {
  form: FormGroup;
  selectedFile: File | null = null;

  submit(form: FormGroup) {}
  constructor(
    private fb: FormBuilder,
    private productService: ProductService,
    private http: HttpClient,
    private dialogRef: MatDialogRef<AddProductComponent>,
    private router: Router,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.form = new FormGroup({
      productName: new FormControl(null, Validators.required),
      productPrice: new FormControl(null, Validators.required),
      productImage: new FormControl(null, Validators.required),

      productCategory: new FormControl(null, Validators.required),
      productDescription: new FormControl(null, Validators.required),
    });
  }
  productImage: string;
  addProduct() {
    const productName = this.form.get('productName').value;
    const productCategory = this.form.get('productCategory').value;
    const productDescription = this.form.get('productDescription').value;
    const productPrice = this.form.get('productPrice').value;
    console.log(productName + 'is the name');
    const product = {
      name: productName,
      imageURL: this.productImage,
      category: productCategory,
      price: productPrice,
      description: productDescription,
    };
    this.productService.addProduct(product).subscribe(
      (response) => {
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
      formData.append('image', this.selectedFile, this.selectedFile.name);

      this.http
        .post(
          'https://deliveryapp-production-4114.up.railway.app/upload',
          formData,
          {
            responseType: 'text',
          }
        )
        .subscribe(
          (response) => {
            this.productImage = response;
            console.log('File uploaded successfully', response);
          },
          (error) => {
            console.error('Error uploading file:', error);
          }
        );
    }
  }
}
