import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, NgForm } from '@angular/forms';

import { ManagerService } from 'src/app/services/manager-service/manager.service';
import { AddCustomer } from '../../model/AddCustomer';
import { ActivatedRoute } from '@angular/router';
import { __values } from 'tslib';
import { map } from 'rxjs';
import { DatePipe } from '@angular/common';
import { ValidationMessage } from 'src/app/model/validation-message';


@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css']
})
export class AddCustomerComponent implements OnInit {
  errorMessage = 'Invalid Credentials';
  successMessage: any;
  invalidLogin = false;
  loginSuccess = false;
  isEditable = false;
  customerId!:number;
  customer:any;
  urlPath!:string;
  Gender!:string;
  isSelectedMale:boolean=false;
  isSelectedFemale:boolean = false; 
  Title="Add Customer";
  fieldTextType: boolean=false;
  fieldTextType2: boolean=false;
  datePipe = new DatePipe('en-US');
  maxDate=this.datePipe.transform(new Date(), 'yyyy-MM-dd')
  futureDateError: boolean=false;
  validationMessage=ValidationMessage;
  constructor(private managerService:ManagerService,private route:ActivatedRoute) { }


  ngOnInit(): void {
  
    
  }
  addCustomer(form:AddCustomer): void {
    console.log(form);
    this.managerService.addNewCustomer(form).subscribe(resp=>{
      console.log(resp);
      this.loginSuccess=true;
      this.successMessage=resp;


    },(error)=>{
      this.errorMessage=error.message;
      this.invalidLogin=true;
    })

}



toggleFieldTextType(id:string) {
  if(id=='password'){
    this.fieldTextType = !this.fieldTextType;
  }
  else if(id=='confirmPassword')
  {
    this.fieldTextType2= !this.fieldTextType2;
  }
 
}

emailChange(email:any){
  console.log(email);
}

}
