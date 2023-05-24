import { Component } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { UserService } from '../_services/user.service';
import { UsreAuthService } from '../_services/usre-auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
})
export class LoginFormComponent {
  loginForm: FormGroup;
  constructor(
    private builder: FormBuilder,
    private userService: UserService,
    private userAuthService: UsreAuthService,
    private router: Router
  ) {}
  ngOnInit() {
    this.loginForm = new FormGroup({
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, Validators.required),
    });
  }
  onSubmit(FormData: any) {
    this.userService.login(this.loginForm.value).subscribe(
      (res) => {
        // console.log(res);
        this.userAuthService.setRole(res.role);
        this.userAuthService.setToken(res.access_token);

        const role = res.role;
        if (role === 'ADMIN') {
          this.router.navigate(['/admin']);
        } else {
          this.router.navigate(['/products']);
        }
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
