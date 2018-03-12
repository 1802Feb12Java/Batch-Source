//Component that holds all other components

import { Component } from '@angular/core';

//Decorator: metadata of component
@Component({
  selector: 'app-root', //HTML tag for this component
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

//Must export so app-module can pull
export class AppComponent {
  title = 'app';
}
