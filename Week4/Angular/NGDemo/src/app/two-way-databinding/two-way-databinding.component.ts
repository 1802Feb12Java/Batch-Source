import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-two-way-databinding',
  templateUrl: './two-way-databinding.component.html',
  styleUrls: ['./two-way-databinding.component.css']
})
export class TwoWayDatabindingComponent implements OnInit {

  //constructor code block
  constructor() { }

  public user = {
    email: '',
    pasword: ''
  };

  //static code block
  ngOnInit() {
  }

}