import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { ProductDetailsComponent } from './home/product-details/product-details.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { CartComponent } from './home/cart/cart.component';
import { CheckoutFormComponent } from './checkout-form/checkout-form.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AdminComponent } from './admin/admin.component';
import { TrackOrderComponent } from './track-order/track-order.component';
import { AuthGuard } from './_auth/auth.guard';
import { OrderListComponent } from './order-list/order-list.component';

const routes: Routes = [
  { path: 'products', component: HomeComponent },
  { path: 'products/:id', component: ProductDetailsComponent },
  { path: 'contactUs', component: ContactUsComponent },
  {
    path: 'cart',
    component: CartComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN', 'CUSTOMER'] },
  },
  {
    path: 'cart/checkout',
    component: CheckoutFormComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN', 'CUSTOMER'] },
  },
  {
    path: 'orders',
    component: OrderListComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN', 'CUSTOMER'] },
  },
  {
    path: 'orders/:orderId',
    component: TrackOrderComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN', 'CUSTOMER'] },
  },
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterFormComponent },
  {
    path: 'admin',
    component: AdminComponent,
    canActivate: [AuthGuard],
    data: { roles: ['ADMIN'] },
  },
  { path: 'forbidden', component: ForbiddenComponent },
  { path: '**', component: HomeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
