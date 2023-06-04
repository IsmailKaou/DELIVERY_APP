import { Component, OnInit } from '@angular/core';
import { OrderService } from '../_services/order.service';
import { ActivatedRoute, Route } from '@angular/router';
import { Order } from '../model/Order';

@Component({
  selector: 'app-track-order',
  templateUrl: './track-order.component.html',
  styleUrls: ['./track-order.component.css'],
})
export class TrackOrderComponent implements OnInit {
  constructor(
    private orderService: OrderService,
    private route: ActivatedRoute
  ) {}
  /* Declaring a variable `order` of type `Order`. This variable is likely to be used to store the
  order details retrieved from the `getOrderById()` method. */
  order: Order;
  orderStatus = {
    PROCESSING: 1,
    PLACED: 2,
    SHIPPED: 3,
    DELIVERED: 4,
  };
  currentStep: number | undefined;
  //expectedArrivalDate: string = '16/05/25';
  ngOnInit(): void {
    this.getOrderById();
  }

  getOrderById() {
    this.orderService
      .getOrder(this.route.snapshot.paramMap.get('orderId'))
      .subscribe(
        (order) => {
          this.order = order;
          this.currentStep = this.orderStatus[this.order.orderStatus];
        },
        (err) => {
          console.log(err);
        }
      );
  }
}
