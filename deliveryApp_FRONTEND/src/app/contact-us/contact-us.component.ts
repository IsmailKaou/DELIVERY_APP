import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { EmailService } from '../_services/email.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-contact-us',
  templateUrl: './contact-us.component.html',
  styleUrls: ['./contact-us.component.css'],
})
export class ContactUsComponent implements OnInit {
  contactForm: FormGroup;

  endpointUrl: string = '/api/ws';

  constructor(
    private builder: FormBuilder,
    private contact: EmailService,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.contactForm = new FormGroup({
      subject: new FormControl(null, Validators.required),
      email: new FormControl(null, [Validators.required, Validators.email]),
      body: new FormControl(null, Validators.required),
    });
  }

  onSubmit(FormData: any) {
    this.contactForm.value.name = null;
    this.contactForm.value.email = null;
    this.contactForm.value.body = null;
    this.contact.sendEmail(FormData).subscribe(
      (response) => {
        location.href = 'https://mailthis.to/confirm';
      },
      (error) => {
        console.warn(error.responseText);
        console.log({ error });
      }
    );
  }
}
