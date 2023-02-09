import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAccountComponent } from './components/add-account/add-account.component';
import { AddCustomerComponent } from './components/add-customer/add-customer.component';
import { AuthGuard } from './auth/auth.guard';
import { BalanceEnquiryComponent } from './components/balance-enquiry/balance-enquiry.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { DepositComponent } from './components/deposit-withdraw/deposit.component';
import { FundTransferComponent } from './components/fund-transfer/fund-transfer.component';
import { HomeComponent } from 'src/app/components/home/home.component';
import { LoginComponent } from 'src/app/components/login/login.component';
import { MinistatementComponent } from 'src/app/components/ministatement/ministatement.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { ProfileComponent } from './components/profile/profile.component';
import { EditAccountComponent } from './components/edit-account/edit-account.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';
import { EditCustomerComponent } from './components/edit-customer/edit-customer.component';

const routes: Routes = [
  // { path: '', component: HomeComponent },
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },

  { path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
  { path: 'balanceEnquiry', component: BalanceEnquiryComponent, canActivate: [AuthGuard] },
  { path: 'deleteAccount', component: BalanceEnquiryComponent, canActivate: [AuthGuard] },
  { path: 'updateAccount', component: BalanceEnquiryComponent, canActivate: [AuthGuard] },
  { path: 'deleteCustomer', component: BalanceEnquiryComponent, canActivate: [AuthGuard] },
  { path: 'updateCustomer', component: BalanceEnquiryComponent, canActivate: [AuthGuard] },
  { path: 'editCustomer', component: EditCustomerComponent, canActivate: [AuthGuard] },
  { path: 'editAccount', component: EditAccountComponent, canActivate: [AuthGuard] },
  { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
  { path: 'addCustomer', component: AddCustomerComponent, canActivate: [AuthGuard] },
  { path: 'addAccount', component: AddAccountComponent, canActivate: [AuthGuard] },
  { path: 'deposit', component: DepositComponent, canActivate: [AuthGuard] },
  {
    path: 'withdraw', component: DepositComponent, canActivate: [AuthGuard]
  }
  ,
  {
    path: 'fundTransfer', component: FundTransferComponent, canActivate: [AuthGuard]
  },
  {path:'miniStatement',component:MinistatementComponent, canActivate: [AuthGuard]},
  {path:'changePassword',component:ChangePasswordComponent, canActivate: [AuthGuard]},
  
  { path: 'logout', redirectTo: 'login' },
  { path: "**", component: NotFoundComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
