import { Component } from '@angular/core';
import { ProductService } from '../_services/product.service';
import { CheckoutService } from '../_services/checkout.service';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-checkout-form',
  templateUrl: './checkout-form.component.html',
  styleUrls: ['./checkout-form.component.css'],
})
export class CheckoutFormComponent {
  cartDetails: any[] = [];
  totalCart: number = 0;
  shippingPrice: number;
  isDataLoaded: boolean = false;
  deliveryModeT;
  constructor(
    private productService: ProductService,
    private checkoutService: CheckoutService,
    private formBuilder: FormBuilder,
    private router: Router
  ) {}
  myForm: FormGroup;
  ngOnInit(): void {
    this.getCartDetails();
    this.myForm = this.formBuilder.group({
      cardNumber: [
        '',
        [
          Validators.required,
          Validators.minLength(16),
          Validators.maxLength(16),
        ],
      ],
      cardHolder: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      expirationDate: ['', Validators.required],
      cvv: [
        '',
        [Validators.required, Validators.minLength(3), Validators.maxLength(4)],
      ],
      billingAddress: ['', Validators.required],
      deliveryMode: [' ', Validators.required],
    });
    // console.log(this.deliveryModeT);
  }
  onChange(e) {
    // console.log(e.target.value);
    this.shippingPrice = e.target.value == 'Express' ? 59 : 15;
  }

  placeOrder() {
    this.checkoutService.processPayment(this.myForm.value).subscribe(
      (res) => {
        this.router.navigate(['/orders/' + res.orderId]);
      },
      (err) => {
        console.log(err);
      }
    );
  }
  getCartDetails() {
    this.productService.getCartDetails().subscribe(
      (res: any) => {
        console.log(res);
        // console.log(res[0].quantity);

        this.cartDetails = res;
        this.totalCart = 0;
        for (let index = 0; index < res.length; index++) {
          this.totalCart += res[index].quantity * res[index].product.price;
        }
        this.isDataLoaded = true;
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
