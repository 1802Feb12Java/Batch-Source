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

  // params: HttpParams = new HttpParams();

  // approve(reimbID: number){
  //   console.log(reimbID);
  //   // this.params = this.params.append('reqid', reimbID.toString());
  //   // this.params = this.params.append('newstatus', '2');
  //   this.client.post("http://localhost:8080/Project1/reimbursements", 
  //     {
  //       "reqId": reimbID,
  //       "newstatus": 2
  //     }).subscribe(
  //       (val) => {
  //           console.log("POST call successful value returned in body", 
  //                       val);
  //       });
  //   // this.ngOnInit();
  // }
}