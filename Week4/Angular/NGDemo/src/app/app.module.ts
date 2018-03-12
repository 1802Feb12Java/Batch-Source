import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TwoWayDatabindingComponent } from './two-way-databinding/two-way-databinding.component';
import { PropertyBindingComponentComponent } from './property-binding-component/property-binding-component.component';
import { EventBindingComponentComponent } from './event-binding-component/event-binding-component.component';
import { PipeComponent } from './pipe/pipe.component';

import { PostService } from './shared/post.service';
import { PostComponent } from './post/post.component';

//Pull everything that gets loaded into final project
@NgModule({
  declarations: [
    AppComponent,
    TwoWayDatabindingComponent,
    PropertyBindingComponentComponent,
    EventBindingComponentComponent,
    PipeComponent,
    PostComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpModule
  ],
  providers: [PostService],
  //Defines entry point for application
  bootstrap: [AppComponent]
})
export class AppModule { }
