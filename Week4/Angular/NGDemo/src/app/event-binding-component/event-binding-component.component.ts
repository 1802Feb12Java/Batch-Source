import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-event-binding-component',
  templateUrl: './event-binding-component.component.html',
  styleUrls: ['./event-binding-component.component.css']
})
export class EventBindingComponentComponent implements OnInit {

  public counter: number = 0;

  public incrementCounter() {
    this.counter++;
  }

  constructor() { }

  ngOnInit() {
  }

}
