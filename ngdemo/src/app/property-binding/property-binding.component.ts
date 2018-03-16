import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-property-binding',
  templateUrl: './property-binding.component.html',
  styleUrls: ['./property-binding.component.css']
})
export class PropertyBindingComponent implements OnInit {

  public notes = {
    question: 'What is property binding?',
    answer: `Write a template property
    binding to set a property of a view element.
    The binding sets the property to the value of a template expression.
    
    It can also be used to provide input to child components.`
  };

  public styleObject = {
    color: 'red',
    border: '1px solid black',
    cursor: 'pointer',
    margin: '2em',
    padding: '2em'
  };

  public changeStyle() {
    this.styleObject.color = 'blue';
    this.styleObject.border = '2px groove purple';
  }

  constructor() { }

  ngOnInit() {
  }

}
