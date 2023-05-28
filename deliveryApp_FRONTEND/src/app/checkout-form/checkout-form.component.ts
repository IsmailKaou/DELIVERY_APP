import { Component } from '@angular/core';
import { ProductService } from '../_services/product.service';

@Component({
  selector: 'app-checkout-form',
  templateUrl: './checkout-form.component.html',
  styleUrls: ['./checkout-form.component.css'],
})
export class CheckoutFormComponent {
  cartDetails: any[] = [];
  totalCart: number = 0;
  isDataLoaded: boolean = false;
  constructor(private productService: ProductService) {}
  ngOnInit(): void {
    this.getCartDetails();
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
