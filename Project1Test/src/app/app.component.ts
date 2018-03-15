import { Component } from '@angular/core';
import { LoggedInGuard } from './logged-in.guard';
import { ManagerGuard } from './manager.guard';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';
}
