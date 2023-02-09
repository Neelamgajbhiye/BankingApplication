import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { LoginService } from 'src/app/services/login-service/login.service';
import { ManagerService } from 'src/app/services/manager-service/manager.service';

@Component({
  selector: 'app-balance-enquiry',
  templateUrl: './balance-enquiry.component.html',
  styleUrls: ['./balance-enquiry.component.css']
})
export class BalanceEnquiryComponent implements OnInit {

  balance:any;
  invalidLogin = false;
  errorMessage = 'Invalid Credentials';
  successMessage="Your Current balance is :"
  loginSuccess=false;
  title='';
  accountNumber='';
  label='';
  placeholder='';
  constructor(private managerService : ManagerService,private customerService:CustomerService,private router:Router) { }

  ngOnInit(): void {
    switch(this.router.url)
   {
    case '/balanceEnquiry':
      {
        this.accountNumber='accountNumber';
        this.label='Account Number';
        this.title='Balance Enquiry';
        this.placeholder='Enter Account Number';
      }
      break;
      case '/deleteAccount':
        {
          this.accountNumber='accountNumber';
          this.label='Account Number';
          this.title='Delete Account';
          this.placeholder='Enter Account Number';
        }
      break;
      case '/deleteCustomer':{
        this.accountNumber='customerId';
        this.label='Customer Id';
        this.title='Delete Customer'
        this.placeholder='Enter Customer Id';

      }

        
      break;
      case '/updateAccount':
        {
          this.accountNumber='accountNumber';
          this.label='Account Number';
          this.title='Update Account';
          this.placeholder='Enter Account Number';
        }
      break;
      case '/updateCustomer':{
        this.accountNumber='customerId';
        this.label='Customer Id';
        this.title='Update Customer'
        this.placeholder='Enter Customer Id';

      }break;
   }
  }
  handlebalanceEnquiry(form:any){
    if(form.accountNumber!==''||form.customerId!=='')
    {
      switch(this.router.url)
   {
    case '/balanceEnquiry':if(this.managerService.isManager())
    {
     this.balanceEnquiryManager(form);
    }
    else{
     this.balanceEnquiryCustomer(form);
    }
      break;
      case '/deleteAccount':this.deleteAccount(form);
      break;
      case '/deleteCustomer':this.deleteCustomer(form);
      break;
      
      case '/updateAccount':this.updateAccount(form);
      break;
      case '/updateCustomer':this.updateCustomer(form);
      break;
   }
      
    }
    else{
      this.invalidLogin=true;
      this.loginSuccess=false;
      this.errorMessage="please enter a valid account number";
    }


  }
  updateAccount(form: any) {
    this.managerService.isAccountUnderManager(form.accountNumber).subscribe(response=>{
      console.log(response);
      sessionStorage.setItem('editAccount', JSON.stringify(response));
      this.router.navigate(['/editAccount'],{queryParams:{accountNumber:form.accountNumber}});
    },error=>{
      console.log(error);
        this.invalidLogin = true;
        this.loginSuccess=false;
        this.errorMessage=error.message;
    });
  
  }
  deleteAccount(form: any) {
    this.managerService.deleteAccount(form.accountNumber).subscribe(response=>{
      console.log(form.accountNumber);
      this.balance=response;
      this.invalidLogin=false;
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
  deleteCustomer(form: any) {
    this.managerService.deleteCustomer(form.customerId).subscribe(response=>{
      console.log(form);
      this.balance=response;
      this.invalidLogin=false;
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


balanceEnquiryCustomer(form: any) {
  this.customerService.balanceEnquiry(form.accountNumber).subscribe(response=>{
    //console.log(response);
    this.balance=response;
    this.invalidLogin=false;
    this.loginSuccess=true;
    this.successMessage=this.successMessage+this.balance;
  },(error) => {
    console.log(error);
    this.invalidLogin = true;
    this.loginSuccess=false;
    this.errorMessage=error.message;
    
  }
   
  )
}

balanceEnquiryManager(form: any) {
  this.managerService.balanceEnquiry(form.accountNumber).subscribe(response=>{
    //console.log(response);
    this.balance=response;
    this.invalidLogin=false;
    this.loginSuccess=true;
    this.successMessage=this.successMessage+this.balance;
  },(error) => {
    console.log(error);
    this.invalidLogin = true;
    this.loginSuccess=false;
    this.errorMessage=error.message;
    
  }
   
  )
}

updateCustomer(form:any){
this.managerService.isCustomerUnderManager(form.customerId).subscribe(response=>{
  console.log(response);
  sessionStorage.setItem('editCustomer', JSON.stringify(response));
  this.router.navigate(['/editCustomer'],{queryParams:{customerId:form.customerId}});
},error=>{
  console.log(error);
    this.invalidLogin = true;
    this.loginSuccess=false;
    this.errorMessage=error.message;
});
 
  //, { queryParams: { serviceId: serviceId} }
}

}
