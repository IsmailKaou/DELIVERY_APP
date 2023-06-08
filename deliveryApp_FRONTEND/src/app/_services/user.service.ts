import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UsreAuthService } from './usre-auth.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  constructor(
    private http: HttpClient,
    private userAuthService: UsreAuthService
  ) {}
  api = 'http://localhost:2000';
  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  public login(loginData) {
    return this.http.post<any>(
      this.api + '/api/v1/auth/authenticate',
      loginData,
      {
        headers: this.requestHeader,
      }
    );
  }
  public register(registerData) {
    return this.http.post<any>(
      this.api + '/api/v1/auth/register',
      registerData,
      {
        headers: this.requestHeader,
      }
    );
  }

  // ? test for secured backend routes
  public forAdmin() {
    return this.http.get(this.api + '/forAdmin', {
      responseType: 'text',
    });
  }
  public roleMatch(allowedRoles: string[]): boolean {
    const userRole: string = this.userAuthService.getRole();
    if (userRole != null) {
      if (allowedRoles.includes(userRole)) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
