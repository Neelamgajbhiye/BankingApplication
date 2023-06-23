import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AddAccount } from 'src/app/model/AddAccount';
import { AddCustomer } from 'src/app/model/AddCustomer';
import { LoginService } from '../login-service/login.service';


@Injectable({
  providedIn: 'root'
})
export class ManagerService {
 
  private baseUrl = 'http://localhost:8080/api/user'; 
  constructor(private http: HttpClient, private router : Router,private loginService:LoginService) { }
  isManager(){
    if(this.loginService.isLoggedIn && this.loginService.data.personalInfo.user.role.roleName=='manager'){

      return true;
    }
    return false;
  }
  addNewCustomer(customer : AddCustomer) : Observable<string>  
  {  
      let url = this.baseUrl + "/addcustomer"; 
      console.log(customer); 
      return this.http.post(url,customer,{responseType:'text'});
      // .pipe(
      //   map(resp=>{
      //     this.data=resp;
      //     return resp;
      //   })
      //);
      // .pipe(

      //   retry(1),
   
      //   catchError(this.handleError)
   
      // );
     
  } 

  addNewAccount(account : AddAccount) : Observable<string>  
  {  
      let url = this.baseUrl + "/newaccount"; 
      console.log(account); 
      return this.http.post(url, account,{responseType:'text'});
   
     
  } 

  deposit(depositModel:any): Observable<string>{
    let url = this.baseUrl + "/depositaccountmanager"; 
      //console.log(account); 
      return this.http.put(url,depositModel, {responseType:'text'})
   
  }

  withdraw(depositModel:any): Observable<string>{
    let url = this.baseUrl + "/withdrawaccountmanager"; 
      //console.log(account); 
      return this.http.put(url,depositModel, {responseType:'text'})
   
  }

  fundTransfer(fundTransferModel:any):Observable<string>{
    let url=this.baseUrl + "/fundtransfermanager";
    return this.http.put(url,fundTransferModel, {responseType:'text'})

  }

  miniStatement(accountNumber:number):Observable<object>{
    let url=this.baseUrl + "/ministatementmanager/"+`${accountNumber}`;
    return this.http.get(url);
  }

  deleteAccount(accountNumber:number):Observable<string>{
    let url=this.baseUrl + "/deleteaccount/";
    console.log(accountNumber);
    return this.http.delete(url+`${accountNumber}`,{responseType:'text'})
  }

  deleteCustomer(customerId:number):Observable<string>{
    let url=this.baseUrl + "/deletecustomer/";

    return this.http.delete(url+`${customerId}`,{responseType:'text'})
  }
  balanceEnquiry(accountNumber : any) : Observable<object>  
  {   
    //console.log(this.data);
      let url = this.baseUrl + "/managerbalance/"+`${accountNumber}`; 
      console.log(accountNumber); 
      return this.http.get(url);
      // .pipe(

      //   retry(1),
   
      //   catchError(this.handleError)
   
      // );
  } 

  isCustomerUnderManager(customerId: number): Observable<object> {
    let url=this.baseUrl+"/customer/";
    return this.http.get(url+`${customerId}`);
  }
  isAccountUnderManager(accountNumber: number): Observable<object> {
    let url=this.baseUrl+"/account/";
    return this.http.get(url+`${accountNumber}`);
  }
  updateCustomer(form: AddCustomer,customerId:number) {
    let url = this.baseUrl + "/editcustomer/"; 
   return this.http.put(url+`${customerId}`,form);
  }
  updateAccount(form: any, accountNumber: number) {
    let url = this.baseUrl + "/editaccount/"; 
    return this.http.put(url+`${accountNumber}`,form);
  }
 
  isUsernameTaken(username: string): Observable<string>{
    let url = this.baseUrl + "/usernametaken/"; 
    return this.http.get(url+`${username}`,{responseType:'text'});
  }
}
