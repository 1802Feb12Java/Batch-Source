import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private client: HttpClient) { }

  ngOnInit() {
  }

  logout(){
    this.client.get("http://localhost:8080/Project1/logout", {withCredentials:true});
  }
}
