import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { ManagerService } from 'src/app/services/manager-service/manager.service';

@Component({
  selector: 'app-edit-account',
  templateUrl: './edit-account.component.html',
  styleUrls: ['./edit-account.component.css']
})
export class EditAccountComponent implements OnInit {
  errorMessage = 'Invalid Credentials';
  successMessage: string="Account created successfully";
  invalidLogin = false;
  loginSuccess = false;
  isEditable = false;
  accountNumber!: number;
  account!:any;
  @ViewChild('editAccountForm') accountForm!: NgForm;
  constructor(private managerService:ManagerService,private route:ActivatedRoute) { }

  ngOnInit(): void {
    this.route.queryParams.subscribe(params=>{
      console.log(params);
      this.accountNumber = params['accountNumber']
      });
this.account=JSON.parse(sessionStorage.getItem('editAccount')!);

 
      setTimeout(() => { 
        console.log(this.accountForm);
        console.log(this.account);
       this.accountForm.setValue({
        accountNumber:this.account.accountNumber,
customerId: this.account.customer.personId,
accountBalance:this.account.accountBalance ,
accountType:this.account.accountType
       })
     

      });
  }

  editAccount(form:any): void {

    console.log(form);
    
    this.managerService.updateAccount(form,this.accountNumber).subscribe(resp=>{
      
      this.loginSuccess=true;
      this.successMessage="Account Updated successfully";
    this.invalidLogin=false;
    
    //console.log(resp)});
},(error)=>{
  this.errorMessage=error.message;
  this.invalidLogin=true;
  this.loginSuccess=false;
})
}

}
