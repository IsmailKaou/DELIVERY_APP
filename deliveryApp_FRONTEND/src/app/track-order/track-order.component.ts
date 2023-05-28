import { Component, OnInit } from '@angular/core';
import { OrderService } from '../_services/order.service';
import { ActivatedRoute, Route } from '@angular/router';

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
  ngOnInit(): void {
    this.getOrderById();
  }

  getOrderById() {
    this.orderService
      .getOrder(this.route.snapshot.paramMap.get('orderId'))
      .subscribe(
        (order) => {
          console.log(order);
        },
        (err) => {
          console.log(err);
        }
      );
  }
  currentStep: number = 4; // Set the current step based on the order's status
  expectedArrivalDate: string = '01/12/19'; // Replace with the actual expected arrival date
  trackingNumber: string = '234094567242423422898'; // Replace with the actual tracking number
}
