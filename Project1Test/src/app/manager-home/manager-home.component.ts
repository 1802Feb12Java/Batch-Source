import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-manager-home',
  templateUrl: './manager-home.component.html',
  styleUrls: ['./manager-home.component.css']
})
export class ManagerHomeComponent implements OnInit {

  private empList: any = [];
  private sessionID: any;

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

    this.client.get('http://localhost:8080/Project1/employeeList', { withCredentials: true })
    .subscribe(
      (succ: any) => {
        this.empList = succ;
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
