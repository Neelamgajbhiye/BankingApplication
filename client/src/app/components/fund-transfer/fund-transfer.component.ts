import { Component, OnInit } from '@angular/core';
import { CustomerService } from 'src/app/services/customer-service/customer.service';
import { ManagerService } from 'src/app/services/manager-service/manager.service';

@Component({
  selector: 'app-fund-transfer',
  templateUrl: './fund-transfer.component.html',
  styleUrls: ['./fund-transfer.component.css']
})
export class FundTransferComponent implements OnInit {
  invalidLogin = false;
  errorMessage = 'Invalid Credentials';
  successMessage: string="";
  loginSuccess=false;
  constructor(private managerService : ManagerService,private customerService:CustomerService) { }

  ngOnInit(): void {
  }

  handlefundTransfer(form:any){
   // if(form.valid)
    if(this.managerService.isManager()){
      this.handlefundTransferManager(form);
    }
    else{
      this.handlefundTransferCustomer(form);
    }
    

  }
  handlefundTransferCustomer(form: any) {
    this.customerService.fundTransfer(form).subscribe(resp=>{
      this.loginSuccess=true;
      this.invalidLogin=false;
      this.successMessage=resp;
    },(error)=>{
      this.invalidLogin=true;
      this.loginSuccess=false;
      this.errorMessage=error.message; 
    })
  }
  handlefundTransferManager(form: any) {
    this.managerService.fundTransfer(form).subscribe(resp=>{
      this.loginSuccess=true;
      this.invalidLogin=false;
      this.successMessage=resp;
    },(error)=>{
      this.invalidLogin=true;
      this.loginSuccess = false;
      this.errorMessage=error.message; 
    })
  }

}
