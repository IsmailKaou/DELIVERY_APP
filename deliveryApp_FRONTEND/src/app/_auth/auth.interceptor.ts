import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
} from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { UsreAuthService } from '../_services/usre-auth.service';
import { Router } from '@angular/router';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(
    private userAuthService: UsreAuthService,
    private router: Router
  ) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    if (request.headers.get('No-Auth') === 'True') {
      return next.handle(request.clone());
    }
    const token = this.userAuthService.getToken();

    // if the request does not contain the no auth in header we inject the token on it
    const req = this.addToken(request, token);
    return next.handle(req).pipe(
      catchError((err: HttpErrorResponse) => {
        console.log(err.status);
        // if the user is not logged in
        if (err.status === 401) {
          this.router.navigate(['/login']);
        } else if (err.status === 403) {
          // if the user is not authorized
          this.router.navigate(['/forbidden']);
        }
        return throwError('Something went wrong');
      })
    );
  }

  private addToken(request: HttpRequest<any>, token: string) {
    return request.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      },
    });
  }
}
