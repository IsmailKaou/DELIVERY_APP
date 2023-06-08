import { Component, OnInit } from '@angular/core';
import { OrderService } from '../_services/order.service';
import { Order } from '../model/Order';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css'],
})
export class OrderListComponent implements OnInit {
  constructor(private orderService: OrderService, private router: Router) {}
  ngOnInit(): void {
    this.listOrders();
  }
  orders: Order[];
  showOrderLine(orderId) {
    this.router.navigate(['/orders/' + orderId]);
  }

  listOrders() {
    this.orderService.getOrders().subscribe(
      (orders) => {
        console.log(orders);
        this.orders = orders;
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
