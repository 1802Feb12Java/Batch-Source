import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CurrentUserService } from '../shared/current-user.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private currUser: CurrentUserService, private title: Title) { }

  ngOnInit() {
    this.title.setTitle('Login Page');
    if(this.currUser.getCurrentUser() != null){
      window.location.href='http://localhost:8080/Project1/logout';
      sessionStorage.clear();
      alert("You have been logged out. Have a great day!");
    }
  }
}
