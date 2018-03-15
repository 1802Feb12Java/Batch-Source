import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { PARAMETERS } from '@angular/core/src/util/decorators';

@Component({
  selector: 'app-reimbursements-all',
  templateUrl: './reimbursements-all.component.html',
  styleUrls: ['./reimbursements-all.component.css']
})
export class ReimbursementsAllComponent implements OnInit {

  reimbursementList: any = [];
  empList: any = [];

  constructor(private client: HttpClient) { }

  ngOnInit() {
    this.client.get('http://localhost:8080/Project1/reimbursements', { withCredentials: true })
      .subscribe(
        (succ: any) => { 
          this.reimbursementList = succ;
          console.log(this.reimbursementList);
          return this.reimbursementList;
        },
        err => {
          alert('failed to retrieve reimbursements');
        }
    );

    this.client.get('http://localhost:8080/Project1/employeeList', { withCredentials: true })
    .subscribe(
      (succ: any) => { 
        this.empList = succ;
        console.log(this.empList);
        return this.empList;
      },
      err => {
        alert('failed to retrieve reimbursements');
      }
    );  
    document.getElementById("modal01").onkeydown = function() {
      document.getElementById("modal01").style.display='none';
    }
  }

  filterTable() {
    // Declare variables 
    var input, filter, table, tr, td, i;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("myTable");
    tr = table.getElementsByTagName("tr");
  
    // Loop through all table rows, and hide those who don't match the search query
    for (i = 0; i < tr.length; i++) {
      td = tr[i].getElementsByTagName("td")[6]; //grab the uid column
      if (td) {
        if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
          tr[i].style.display = "";
        } else {
          tr[i].style.display = "none";
        }
      } 
    }
  }

  onClick(r_id) {
    for(let r of this.reimbursementList){
      if(r.id == r_id){
        document.getElementById("img01")["src"] = "data:image/jpg;base64,"+r.base64receipt;
      }
    }
    document.getElementById("modal01").style.display = "block";
  }

  escPress(e: KeyboardEvent){
    console.log(e.keyCode);
    if(e.keyCode == 27){
      document.getElementById("modal01").style.display = 'none';
    }
  }
}