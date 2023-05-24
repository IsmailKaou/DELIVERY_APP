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
  selector: 'app-register-form',
  templateUrl: './register-form.component.html',
  styleUrls: ['./register-form.component.css'],
})
export class RegisterFormComponent {
  registerForm: FormGroup;
  constructor(
    private builder: FormBuilder,
    private userService: UserService,
    private userAuthService: UsreAuthService,
    private router: Router
  ) {}
  ngOnInit() {
    this.registerForm = new FormGroup({
      firstName: new FormControl(null),
      lastName: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      password: new FormControl(null, Validators.required),
    });
  }
  onSubmit(FormData: any) {
    // console.log(FormData.email);
    // console.log(FormData.firstName);
    // console.log(FormData.lastName);
    // console.log(FormData.password);
    this.userService.register(this.registerForm.value).subscribe(
      (res) => {
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
