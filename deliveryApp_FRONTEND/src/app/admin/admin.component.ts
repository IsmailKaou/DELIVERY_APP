import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  message: string;
  constructor(private userService: UserService) {}
  ngOnInit(): void {
    this.forAdmin();
  }
  forAdmin() {
    this.userService.forAdmin().subscribe(
      (res) => {
        console.log('im here');

        console.log(res);
        this.message = res;
      },
      (err) => {
        console.log(err);
      }
    );
  }
}
