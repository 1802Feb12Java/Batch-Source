import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { CurrentUserService } from '../shared/current-user.service';
import { User } from '../models/user';

@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
  styleUrls: ['./employee-home.component.css']
})
export class EmployeeHomeComponent implements OnInit {

  sessionID: number = 0;
  empList: Array<any> = [];
  reimbursementList: Array<any> = [];
  allowed: boolean = false;

  constructor(private client: HttpClient, private router: Router, private currUser: CurrentUserService) { }

  ngOnInit() {
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


    this.client.get('http://localhost:8080/Project1/reimbursements', { withCredentials: true })
   .subscribe(
     (succ: any) => {
       this.reimbursementList = succ;
       console.log(this.reimbursementList);   
       return this.reimbursementList;
     },
     err => {
       alert('failed to retrieve reimbursements');
     });


    this.client.get('http://localhost:8080/Project1/employeeList', { withCredentials: true })
    .subscribe(
      (succ: any) => {
        this.empList = succ;
        console.log(this.empList);
        for(let e of this.empList){
          if(e.id == this.sessionID){
            sessionStorage.removeItem("user");
            const user = new User(e.id, e.roleId);
            this.currUser.setUser(user);
          }
        }
        document.getElementById('welcome').innerHTML = "Welcome back, " + this.getName(); //put it in here to wait until the responses were made
        return this.empList;
      },
      err => {
        alert('failed to retrieve employee');
      });      
  }

  getName(): String{
    for(var i=0; i<this.empList.length; i++){
      if(this.empList[i].id == this.sessionID){
        return this.empList[i].firstname + " " + this.empList[i].lastname
      }
    }
  }
  
}
