import { Component } from '@angular/core';

//decorator: metadata for AppComponent class
//anything in the app-root tag is controlled by this component
//@angular/core is the library all this is pulled from
//WILL BE ASKED ABOUT DECORATORS
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
}
