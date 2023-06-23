import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AddCustomer } from 'src/app/model/AddCustomer';
import { ManagerService } from 'src/app/services/manager-service/manager.service';

@Component({
  selector: 'app-edit-customer',
  templateUrl: './edit-customer.component.html',
  styleUrls: ['./edit-customer.component.css']
})
export class EditCustomerComponent implements OnInit {
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
  Title="Edit Customer";
  @ViewChild('addCustomerForm') customerForm!: NgForm;
  constructor(private managerService:ManagerService,private route:ActivatedRoute) { }


  ngOnInit(): void {
  
       
    
     this.route.url.subscribe(url=>{
     this.urlPath= url[0].path
     })


   
      this.route.queryParams.subscribe(params=>{
        console.log(params);
        this.customerId = params['customerId']
        });
this.customer=JSON.parse(sessionStorage.getItem('editCustomer')!);
var datePipe = new DatePipe('en-US');
   
        setTimeout(() => { 
          console.log(this.customerForm);
          if(this.customer.gender=='MALE'){
            this.isSelectedMale=true;
            this.isSelectedFemale=false;
          }
          else if(this.customer.gender=='FEMALE'){
            this.isSelectedMale=false;
            this.isSelectedFemale=true;
          }
          this.customerForm.setValue({
            customerId:this.customerId,
            name:this.customer.personName,
     
           
            
email: this.customer.email,
                      

userName:this.customer.user.username,
  

dateOfBirth: datePipe.transform(this.customer.dob, 'yyyy-MM-dd'),
          

phone:this.customer.telephoneNumber,


flatNumber:this.customer.address.flatNo,

gender:this.customer.gender,
         

city:this.customer.address.city,
   

state:this.customer.address.state,
  

pincode:this.customer.address.pincode,
          })
          // this.customerForm.controls[ustomerId'].setValue();
          // this.customerForm.controls['name'].setValue(this.customer.personName);
          // this.customerForm.controls['emailId'].setValue(this.customer.email);
          // this.customerForm.controls['userName'].setValue(this.customer.user.username);
          // this.customerForm.controls['dateOfBirth'].setValue(this.customer.dob);
          // this.customerForm.controls['phone'].setValue(this.customer.telephoneNumber);
          // this.customerForm.controls['flatNumber'].setValue(this.customer.address.flatNumber);
          // this.customerForm.controls['gender'].setValue(this.customer.gender);
          // this.customerForm.controls['city'].setValue(this.customer.address.city);
          // this.customerForm.controls['state'].setValue(this.customer.address.state);
          // this.customerForm.controls['pincode'].setValue(this.customer.address.pincode);
          


        });
     



    
  }


editCustomer(form:AddCustomer): void {

  console.log(form);
  
  this.managerService.updateCustomer(form,this.customerId).subscribe(resp=>{
    
    this.loginSuccess=true;
      this.successMessage="Customer Updated successfully";
    this.invalidLogin=false;
    
    //console.log(resp)});
},(error)=>{
  this.errorMessage=error.message;
  this.invalidLogin=true;
  this.loginSuccess=false;
})
}



}
