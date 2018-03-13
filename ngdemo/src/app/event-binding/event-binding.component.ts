import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-binding',
  templateUrl: './event-binding.component.html',
  styleUrls: ['./event-binding.component.css']
})
export class EventBindingComponent implements OnInit {

public counter = "0";

public increment(){
    this.counter = "game over";
}

public reset(){
  this.counter = "0";
}

  constructor() { }

  ngOnInit() {
  }

}
