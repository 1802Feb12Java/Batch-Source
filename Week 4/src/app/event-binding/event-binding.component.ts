import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-binding',
  templateUrl: './event-binding.component.html',
  styleUrls: ['./event-binding.component.css']
})
export class EventBindingComponent implements OnInit {

  public counter = 0;

  public incrementCounter() {
    this.counter++;
  }

  constructor() { }

  ngOnInit() {
  }

}
