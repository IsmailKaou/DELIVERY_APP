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
  addProduct() {
    const productName = this.form.get('productName').value;
    const productImage = this.form.get('productImage').value;
    const productCategory = this.form.get('productCategory').value;
    const productDescription = this.form.get('productDescription').value;
    const productPrice = this.form.get('productPrice').value;
    console.log(productName + 'is the name');
    const product = {
      name: productName,
      imageURL: productImage,
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
