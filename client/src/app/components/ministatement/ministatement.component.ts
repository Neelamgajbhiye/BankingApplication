import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { ManagerService } from 'src/app/services/manager-service/manager.service';


@Component({
  selector: 'app-ministatement',
  templateUrl: './ministatement.component.html',
  styleUrls: ['./ministatement.component.css']
})
export class MinistatementComponent implements OnInit {
  invalidLogin = false;
  errorMessage = 'Invalid Credentials';
  //successMessage="Your Current balance is :"
  loginSuccess=false;
  transactions:any;
  self:string='';
  constructor(private managerService:ManagerService,private customerService:CustomerService) { }

  ngOnInit(): void {
  }
  handleminiStatement(form:any){
    if(form.accountNumber!=='')
    {
      this.loginSuccess=true;
      this.invalidLogin=false;
      if(this.managerService.isManager()){
        this.miniStatementManager(form);
      }
      else{
        this.miniStatementCustomer(form);
      }
     
    }
    else{
      this.invalidLogin=true;
      this.loginSuccess=false;
      this.errorMessage="please enter a valid account number"
    }

  }

  miniStatementManager(form:any){

    this.managerService.miniStatement(form.accountNumber).subscribe(res=>{
      
      this.transactions=res;
      this.invalidLogin=false;
      
      console.log(this.transactions);
    },(error)=>{
this.loginSuccess=false;
this.invalidLogin=true;
console.log(error);
this.errorMessage=error.message;
    })
  }
  miniStatementCustomer(form:any){

    this.customerService.miniStatement(form.accountNumber).subscribe(res=>{
      
      this.transactions=res;
      this.invalidLogin=false;
      this.loginSuccess=true;
      
      //console.log(this.transactions);
    },(error)=>{
this.loginSuccess=false;
this.invalidLogin=true;
//console.log(error);
this.errorMessage=error.message;
    })
  }
}
