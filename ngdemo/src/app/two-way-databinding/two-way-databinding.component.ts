import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-two-way-databinding',
  templateUrl: './two-way-databinding.component.html',
  styleUrls: ['./two-way-databinding.component.css']
})
export class TwoWayDatabindingComponent implements OnInit {
  //constructor usually used for dependency injection, not initialization
  //constructor() { }

  //replacing constructor in this example
  //instead create a user object with email and password members that are blank
  public user = {email: '', password: ''};

  //ngOnInit is generally used for initialization
  ngOnInit() {
  }

}
