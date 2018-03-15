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
import { ImagePipePipe } from './image-pipe.pipe';
import { EmpHeaderComponent } from './emp-header/emp-header.component';
import { LoggedInGuard } from './logged-in.guard';
import { ManagerGuard } from './manager.guard';
import { CurrentUserService } from './shared/current-user.service';
import { User } from './models/user';
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
    UpdateUserComponent,
    ImagePipePipe,
    EmpHeaderComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot([
      {path: 'login', component: LoginComponent},
      {path: 'reimbursements', component: ReimbursementsAllComponent, canActivate: [LoggedInGuard, ManagerGuard]},
      {path: 'employeeHome', component: EmployeeHomeComponent},
      {path: 'managerHome', component: ManagerHomeComponent},
      {path: 'application', component: ApplicationComponent, canActivate: [LoggedInGuard]},
      {path: 'employeeList', component: EmployeeListComponent, canActivate: [LoggedInGuard, ManagerGuard]},
      {path: 'updateInfo', component: UpdateUserComponent, canActivate: [LoggedInGuard]},
      {path: '**', pathMatch: 'full', redirectTo:'employeeHome'},
      {path: '', component: LoginComponent}
    ])
  ],
  providers: [
    LoggedInGuard, 
    ManagerGuard,
    CurrentUserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }