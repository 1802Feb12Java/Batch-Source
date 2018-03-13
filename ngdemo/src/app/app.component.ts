import { Component } from '@angular/core';


//Decorator: metadata for class
@Component({
  selector: 'app-root',
  //could hardcode in the html right here instead of importing html file
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
}
