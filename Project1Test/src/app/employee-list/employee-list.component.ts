import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {

  empList: Array<any> = [];

  constructor(private client: HttpClient) { }

  ngOnInit() {
    this.client.get('http://localhost:8080/Project1/employeeList', { withCredentials: true })
   .subscribe(
     (succ: any) => {
        this.empList = succ;
        console.log(this.empList);   
        return this.empList;
     },
     err => {
       alert('failed to retrieve employees');
     }

   );
  }
}
