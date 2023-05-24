import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable, map, tap } from 'rxjs';
import { UsreAuthService } from '../_services/usre-auth.service';
import { UserService } from '../_services/user.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private userAuthService: UsreAuthService,
    private router: Router,
    private userService: UserService
  ) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    if (this.userAuthService.getToken() !== null) {
      // we are getting the list of allowed roles from the  route
      const roles = route.data['roles'] as string[];

      if (roles) {
        const match = this.userService.roleMatch(roles);
        if (match) {
          return true;
        } else {
          return this.router.createUrlTree(['/forbidden']);
          return false;
        }
      }
    } else {
      return this.router.createUrlTree(['/login']);
      return false;
    }
  }
}
