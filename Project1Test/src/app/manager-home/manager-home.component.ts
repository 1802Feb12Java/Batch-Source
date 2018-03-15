import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { User } from '../models/user';
import { CurrentUserService } from '../shared/current-user.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit {

  private empList: any = [];
  private sessionID: any;
  private allowed: boolean = false;

  constructor(private client: HttpClient, private router: Router, private currUser: CurrentUserService, private title: Title) { }

  ngOnInit() {
    this.title.setTitle('Manager Home');
    this.client.get('http://localhost:8080/Project1/session', { withCredentials: true })
   .subscribe(
     (succ: any) => {
        if(!succ.uid){  //if it's null
          this.allowed = false;
          this.router.navigate(['/login']);
          alert("You must be logged in to view this page");
        }
        else{
          this.allowed = true;
          this.sessionID = succ.uid; 
          return this.sessionID;
        }
     },
     err => {
       alert('failed to retrieve sessionID');
     });

    this.client.get('http://localhost:8080/Project1/employeeList', { withCredentials: true })
    .subscribe(
      (succ: any) => {
        this.empList = succ;
        for(let e of this.empList){
          if(e.id == this.sessionID){
            if(e.roleId == 1){
              sessionStorage.removeItem("user");
              const user = new User(e.id, e.roleId);
              this.currUser.setUser(user);
              this.allowed = true;
            }
            else{
              this.allowed = false;
              this.router.navigate(['/login']);
              alert("You must be a manager to view this page");
            }
          }
        }
        console.log(this.empList);
        var h1 = document.getElementById("heading");
        console.log(h1);
        for(var i=0; i<this.empList.length; i++) {
          if(this.empList[i].id == this.sessionID) {
            h1.innerHTML = "Welcome back, " + this.empList[i].firstname + " " + this.empList[i].lastname + "!";
          }
        }


        return this.empList;
      },
      err => {
        alert('failed to retrieve employee');
      });     
    
    
  }

}
