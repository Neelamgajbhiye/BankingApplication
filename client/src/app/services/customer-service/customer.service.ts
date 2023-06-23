import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = 'http://localhost:8080/api/user/'; 
  constructor(private http: HttpClient) { }


  balanceEnquiry(accountNumber : any) : Observable<object>  
  {  
    //console.log(this.data);
      let url = this.baseUrl + "customerbalance/"+`${accountNumber}`; 
      console.log(accountNumber); 
      return this.http.get(url);
      // .pipe(

      //   retry(1),
   
      //   catchError(this.handleError)
   
      // );
  } 

  fundTransfer(fundTransferModel:any):Observable<string>{
    let url=this.baseUrl + "fundtransfercustomer";
    return this.http.put(url,fundTransferModel, {responseType:'text'})

  }

  miniStatement(accountNumber:number):Observable<object>{
    let url=this.baseUrl + "ministatementcustomer/"+`${accountNumber}`;
    return this.http.get(url);
  }
}
