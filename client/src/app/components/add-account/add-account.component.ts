import { Component, OnInit, ViewChild } from '@angular/core';

import { AddAccount } from '../../model/AddAccount';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { ManagerService } from 'src/app/services/manager-service/manager.service';

@Component({
  selector: 'app-add-account',
  templateUrl: './add-account.component.html',
  styleUrls: ['./add-account.component.css']
})
export class AddAccountComponent implements OnInit {
  errorMessage = 'Invalid Credentials';
  successMessage: string="Account created successfully";
  invalidLogin = false;
  loginSuccess = false;
  isEditable = false;
  accountNumber!: number;
  Title='Add Account';
  account!:any;
  urlPath!: string;
  @ViewChild('addAccountForm') accountForm!: NgForm;
  constructor(private managerService:ManagerService,private route:ActivatedRoute) { }

  ngOnInit(): void {
     
    
    this.route.url.subscribe(url=>{
      this.urlPath= url[0].path
      })
 
 
      if(this.urlPath==='editAccount')
      {
       this.isEditable=true;
       this.route.queryParams.subscribe(params=>{
         console.log(params);
         this.accountNumber = params['accountNumber']
         });
 this.account=JSON.parse(sessionStorage.getItem('editAccount')!);

    
         setTimeout(() => { 
           console.log(this.accountForm);
          this.accountForm.setValue({
customerId: this.account.customer.personId,
initialDeposit:this.account.initialDeposit,
accountType:this.account.accountType
          })
        
 
         });
      
 
 
 
      }
      else if(this.urlPath==='addAccount'){
       this.isEditable=false;
      }

  }
  addAccount(form:AddAccount): void {
  
    this.managerService.addNewAccount(form).subscribe(resp=>{
      this.loginSuccess=true;
      this.invalidLogin=false;
      console.log(resp);
      this.successMessage=resp;


    },(error)=>{
      this.loginSuccess=false;
      this.errorMessage=error.message;
      this.invalidLogin=true;
    });}

  
    
    // submitAccount(form:AddAccount): void {
    //   if(this.isEditable)
    //   {
    //     this.editAccount(form);
    //   }
    //   else{
    //     this.addAccount(form);
    //   }
    // }
    
}
