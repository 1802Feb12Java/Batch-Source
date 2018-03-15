import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { CurrentUserService } from './shared/current-user.service';

@Injectable()
export class ManagerGuard implements CanActivate {

  constructor(private router: Router, private currUser: CurrentUserService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot){
    let json = JSON.parse(this.currUser.getCurrentUser());
    console.log(json);
    if(json.roleId == 1){
      return true;
    }
    this.router.navigate(['/login']);
    alert("You must be a manager to view this page");
    return false;
  }
}
