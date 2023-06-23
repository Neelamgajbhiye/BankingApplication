import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login-service/login.service';
import { ManagerService } from 'src/app/services/manager-service/manager.service';

@Component({
  selector: 'app-deposit',
  templateUrl: './deposit.component.html',
  styleUrls: ['./deposit.component.css']
})
export class DepositComponent implements OnInit {
  invalidLogin = false;
  errorMessage = 'Invalid Credentials';
  successMessage: string="";
  loginSuccess=false;
  DepositWithdraw='';
  constructor(private managerService : ManagerService,private router: Router) { }

  ngOnInit(): void {
    if(this.router.url=='/deposit'){ this.DepositWithdraw='Deposit'}
    else{this.DepositWithdraw='withdraw'}
  }

  handleDepositWithdraw(form:any){
    console.log(this.router.url);
    if(this.router.url=='/deposit')
    {
      this.handleDeposit(form);
    }
    else if(this.router.url=='/withdraw')
    {
      this.handleWithdraw(form);
    }
 
      }

      handleDeposit(form:any){
        this.managerService.deposit(form).subscribe(response=>{
          console.log(response);
         // this.balance=response;
         this.loginSuccess=true;
          this.successMessage=response;
        },(error) => {
          console.log(error);
          this.invalidLogin = true;
          this.loginSuccess=false;
          this.errorMessage=error.message;
          
        }
         
        )
      }

      handleWithdraw(form:any){
        this.managerService.withdraw(form).subscribe(response=>{
          console.log(response);
         // this.balance=response;
         this.loginSuccess=true;
          this.successMessage=response;
        },(error) => {
          console.log(error);
          this.invalidLogin = true;
          this.loginSuccess=false;
          this.errorMessage=error.message;
          
        }
         
        )
      }

}
