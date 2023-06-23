import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';  
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';  
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { BalanceEnquiryComponent } from './components/balance-enquiry/balance-enquiry.component';
import { ProfileComponent } from './components/profile/profile.component';
import { DepositComponent } from './components/deposit-withdraw/deposit.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { AddAccountComponent } from './components/add-account/add-account.component';

import { AuthService } from './auth/auth.service';
import { AuthGuard } from './auth/auth.guard';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FundTransferComponent } from './components/fund-transfer/fund-transfer.component';
import { ErrorInterceptor } from './auth/errorInterceptor';

import { authInterceptorProviders } from './auth/authInterceptor';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from './components/home/home.component';
import { MinistatementComponent } from './components/ministatement/ministatement.component';
import { EditAccountComponent } from './components/edit-account/edit-account.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { MatchPasswordDirective } from './directives/match-password/match-password.directive';

import { EditCustomerComponent } from './components/edit-customer/edit-customer.component';
import { PasswordPatternDirective } from './directives/password-pattern/password-pattern.directive';
import { ValidateUserNameDirective } from './directives/validate-user-name/validate-user-name.directive';
import { LoginService } from './services/login-service/login.service';
import { ValidateDateofbirthDirective } from './directives/validate-dateofbirth/validate-dateofbirth.directive';
import { EmailPatternDirective } from './directives/email-pattern/email-pattern.directive';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
//import { PhoneLengthDirective } from './directives/phone-length/phone-length.directive';
//import { ToastrModule, ToastNoAnimation, ToastNoAnimationModule } from 'ngx-toastr';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    BalanceEnquiryComponent,
    ProfileComponent,
    DepositComponent,
    NavbarComponent,
    AddCustomerComponent,
    AddAccountComponent,
    HomeComponent,
    NotFoundComponent,
    FundTransferComponent,
    MinistatementComponent,
    EditAccountComponent,
    ChangePasswordComponent,
    PasswordPatternDirective,
    MatchPasswordDirective,
    ValidateUserNameDirective,
    EditCustomerComponent,
    ValidateDateofbirthDirective,
    EmailPatternDirective,
    //PhoneLengthDirective
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,  
    ReactiveFormsModule,  
    HttpClientModule, FontAwesomeModule,
   // BrowserAnimationsModule, // required animations module
   // ToastrModule.forRoot(), 
    //ToastNoAnimationModule.forRoot(), 
  ],
  providers: [LoginService,AuthService,AuthGuard,{
    provide: HTTP_INTERCEPTORS,
    useClass: ErrorInterceptor,
    multi: true
},authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
