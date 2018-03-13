import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule } from '@angular/http';


import { AppComponent } from './app.component';
import { TwoWayDatabindingComponent } from './two-way-databinding/two-way-databinding.component';
import { FormsModule } from '@angular/forms';
import { PropertyBindingComponent } from './property-binding/property-binding.component';
import { EventBindingComponent } from './event-binding/event-binding.component';
import { PipeComponent } from './pipe/pipe.component';
import { PostService } from './shared/post.service';
import { PostComponent } from './post/post.component';

@NgModule({
  declarations: [
    AppComponent,
    TwoWayDatabindingComponent,
    PropertyBindingComponent,
    EventBindingComponent,
    PipeComponent,
    PostComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule
  ],
  providers: [PostService],
  bootstrap: [AppComponent]
})
export class AppModule { }
