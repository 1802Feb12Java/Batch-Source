import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CurrentUserService } from '../shared/current-user.service';

@Component({
  selector: 'app-emp-header',
  templateUrl: './emp-header.component.html',
  styleUrls: ['./emp-header.component.css']
})
export class EmpHeaderComponent implements OnInit {

  constructor(private client: HttpClient, private currUser: CurrentUserService) { }

  ngOnInit() {
  }

  logout(){
    // sessionStorage.removeItem("user");
    // alert("You have logged out. Have a great day!");
    window.location.href="http://localhost:8080/Project1/logout";
  }
}
