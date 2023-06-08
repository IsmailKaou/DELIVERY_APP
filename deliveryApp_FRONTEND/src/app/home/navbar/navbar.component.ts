import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from 'src/app/_services/product.service';
import { UserService } from 'src/app/_services/user.service';
import { UsreAuthService } from 'src/app/_services/usre-auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent {
  constructor(
    private userAuthService: UsreAuthService,
    private router: Router,
    public userService: UserService,
    private productService: ProductService
  ) {}

  public isLoggedIn() {
    return this.userAuthService.isLoggedIn();
  }

  public logout() {
    this.userAuthService.clearLocalStorage();
    this.router.navigate(['/']);
  }
}
