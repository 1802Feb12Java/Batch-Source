import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-home',
  templateUrl: './employee-home.component.html',
  styleUrls: ['./employee-home.component.css']
})
export class EmployeeHomeComponent implements OnInit {

  sessionID: number = 0;
  empList: Array<any> = [];
  reimbursementList: Array<any> = [];

  constructor(private client: HttpClient) { }

  ngOnInit() {
    this.client.get('http://localhost:8080/Project1/session', { withCredentials: true })
   .subscribe(
     (succ: any) => {
       this.sessionID = succ.uid; 
       console.log(this.sessionID);  
       return this.sessionID;
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
