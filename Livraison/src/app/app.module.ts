import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

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

const appRoutes: Routes = [
  { path: 'products', component: HomeComponent },
  { path: 'products/:id', component: ProductDetailsComponent },
  { path: 'contactUs', component: ContactUsComponent },
  { path: 'cart', component: CartComponent },
  { path: 'cart/checkout', component: CheckoutFormComponent },
  { path: 'login', component: LoginFormComponent },
  { path: 'register', component: RegisterFormComponent },
  { path: '**', component: HomeComponent },
];
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
  ],
  imports: [BrowserModule, AppRoutingModule, RouterModule.forRoot(appRoutes)],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
