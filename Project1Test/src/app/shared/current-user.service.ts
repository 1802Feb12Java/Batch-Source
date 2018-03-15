import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/BehaviorSubject';
import { Observable } from 'rxjs/Observable';
import { User } from '../models/user';

@Injectable()
export class CurrentUserService {

  constructor (){}

  private user: User;

  setUser(user: User){ 
    this.user = user;

    sessionStorage.setItem("user", JSON.stringify(this.user));

    console.log("user set to: {id:"+this.user.id+",roleId:"+this.user.roleId+"}");
  }

  getCurrentUser(){
    return sessionStorage.getItem("user");
  }

}