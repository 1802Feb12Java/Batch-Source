import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-application',
  templateUrl: './application.component.html',
  styleUrls: ['./application.component.css']
})
export class ApplicationComponent implements OnInit {

  sessionID: number;

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
  }

}
