import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-update-user',
  templateUrl: './update-user.component.html',
  styleUrls: ['./update-user.component.css']
})
export class UpdateUserComponent implements OnInit {

  sessionID: number = 0;
  empList: Array<any> = [];
  currentUser: any = {};

  constructor(private client: HttpClient, private title: Title) { }

  ngOnInit() {
    this.title.setTitle('Update Info');
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
        this.getCurrentUser();    //put it in here to wait until the responses were made
        return this.empList;
      },
      err => {
        alert('failed to retrieve employee');
      });
  }
  
  getCurrentUser(){
    for(var i = 0; i<this.empList.length; i++){
      console.log(this.empList[i].id);
      if(this.empList[i].id == this.sessionID){
        this.currentUser = this.empList[i];
        console.log(this.currentUser);
        break;
      }
    }
  }
}
