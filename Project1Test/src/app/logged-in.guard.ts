import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { CurrentUserService } from './shared/current-user.service';
 
@Injectable()
export class LoggedInGuard implements CanActivate {
 
    constructor(private router: Router, private currUser: CurrentUserService) { }
 
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      if(this.currUser.getCurrentUser() != null){
        return true;
      }
      this.router.navigate(['/login']);
      alert("You must be logged in to view this page");
      return false;
    }
}
