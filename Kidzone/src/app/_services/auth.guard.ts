import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import {TokenStorageService} from "./token-storage.service";

@Injectable()
 export class OnlyLoggedInUsersGuard implements CanActivate {
  constructor(private tokenService: TokenStorageService,private router: Router ) {};

  canActivate() {
    let token = this.tokenService.getToken()
    if (token) {
      return true;
    } else {
      this.router.navigate(['/login'])
      return false;
    }
  }
}
