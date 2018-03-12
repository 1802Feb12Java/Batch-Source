import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClient, HttpClientModule } from '@angular/common/http'

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';
import { ReimbursementsAllComponent } from './reimbursements-all/reimbursements-all.component';
import { ManagerHomeComponent } from './manager-home/manager-home.component';
import { EmployeeHomeComponent } from './employee-home/employee-home.component';
import { ApplicationComponent } from './application/application.component';
import { HeaderComponent } from './header/header.component';
import { EmployeeListComponent } from './employee-list/employee-list.component';
import { UpdateUserComponent } from './update-user/update-user.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ReimbursementsAllComponent,
    ManagerHomeComponent,
    EmployeeHomeComponent,
    ApplicationComponent,
    HeaderComponent,
    EmployeeListComponent,
    UpdateUserComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login', component: LoginComponent},
      {path: 'reimbursements', component: ReimbursementsAllComponent},
      {path: 'employeeHome', component: EmployeeHomeComponent},
      {path: 'managerHome', component: ManagerHomeComponent},
      {path: 'application', component: ApplicationComponent},
      {path: 'employeeList', component: EmployeeListComponent},
      {path: 'updateInfo', component: UpdateUserComponent},
      {path: '**', pathMatch: 'full', redirectTo:'login'}
    ])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }