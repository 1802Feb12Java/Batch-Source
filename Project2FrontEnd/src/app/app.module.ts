import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { HttpClient, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { PostviewComponent } from './components/postview/postview.component';
import { NewpostComponent } from './components/newpost/newpost.component';
import { SettingsComponent } from './components/settings/settings.component';
import { UpdateprofileComponent } from './components/updateprofile/updateprofile.component';
import { ProfilepageComponent } from './components/profilepage/profilepage.component';
import { HomeComponent } from './home/home.component';
import { ProfileheaderComponent } from './profileheader/profileheader.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    PostviewComponent,
    NewpostComponent,
    SettingsComponent,
    UpdateprofileComponent,
    ProfilepageComponent,
    HomeComponent,
    ProfileheaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login', component: LoginComponent},
      {path: 'home', component: HomeComponent},
      {path: 'profile', component: ProfilepageComponent},
      //{path: 'reimbursements', component: ReimbursementsAllComponent, canActivate: [LoggedInGuard, ManagerGuard]},
      {path: '**', pathMatch: 'full', redirectTo:'login'},
      {path: '', component: LoginComponent}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
