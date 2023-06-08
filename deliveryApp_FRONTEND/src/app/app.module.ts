import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgxPaginationModule } from 'ngx-pagination';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeroSectionComponent } from './home/hero-section/hero-section.component';
import { NavbarComponent } from './home/navbar/navbar.component';
import { ContactUsComponent } from './contact-us/contact-us.component';
import { LoginFormComponent } from './login-form/login-form.component';
import { CheckoutFormComponent } from './checkout-form/checkout-form.component';
import { ProductDetailsComponent } from './home/product-details/product-details.component';
import { CartComponent } from './home/cart/cart.component';
import { RouterModule, Routes } from '@angular/router';
import { ProductsListComponent } from './home/products-list/products-list.component';
import { RegisterFormComponent } from './register-form/register-form.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { AdminComponent } from './admin/admin.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './_auth/auth.interceptor';
import { AuthGuard } from './_auth/auth.guard';
import { UserService } from './_services/user.service';
import { TrackOrderComponent } from './track-order/track-order.component';
import { OrderListComponent } from './order-list/order-list.component';
import { DeleteDialogComponent } from './admin/delete-dialog/delete-dialog.component';
import { UpdateProductComponent } from './admin/update-product/update-product.component';
import { AddProductComponent } from './admin/add-product/add-product.component';
import { MatDialogModule } from '@angular/material/dialog';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeroSectionComponent,
    NavbarComponent,
    ProductDetailsComponent,
    ContactUsComponent,
    CartComponent,
    LoginFormComponent,
    CheckoutFormComponent,
    ProductsListComponent,
    RegisterFormComponent,
    ForbiddenComponent,
    AdminComponent,
    TrackOrderComponent,
    OrderListComponent,
    DeleteDialogComponent,
    UpdateProductComponent,
    AddProductComponent,
  ],
  imports: [
    BrowserModule,
    NgxPaginationModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule,
    MatDialogModule,
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    UserService,
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
