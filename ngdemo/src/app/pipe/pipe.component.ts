import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-pipe',
  templateUrl: './pipe.component.html',
  styleUrls: ['./pipe.component.css']
})
export class PipeComponent implements OnInit {

  currentTime: Date;

  constructor() { 
    setInterval(()=> {this.currentTime = new Date();}, 1000);
  }

  ngOnInit() {
  }

}
