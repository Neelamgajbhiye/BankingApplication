import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';  
import { BehaviorSubject, catchError, map, Observable, retry, Subject, throwError } from 'rxjs';  
 
import { Router } from '@angular/router';  

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
 // private loggedIn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
 private role=sessionStorage.getItem("role");
  private baseUrl = 'http://localhost:8080/api/'; 
  data:any; 
  constructor(private http: HttpClient, private router : Router) { }

  login(user : any) : Observable<object>  
  {  
      let url = this.baseUrl + "user/login"; 
      console.log(user); 
     
      return this.http.post(url, user)
      .pipe(
        map(resp=>{
          this.data=resp;
          sessionStorage.setItem("role",this.data.personalInfo.user.role.roleName);
          this.loggedIn.next(true);
       this.router.navigateByUrl('/dashboard');
          return resp;
        })
      );
      // .pipe(

      //   retry(1),
   
      //   catchError(this.handleError)
   
      // );
     
  } 
  get isLoggedIn() {
    return this.loggedIn.asObservable();
  }

 
 

//   isLoggedIn(): boolean {
// if(this.data!==null)
// {
//   return true;
// }
// return false;
//   }

changePassword(form:any): Observable<string>{
  let url = this.baseUrl + "user/changepassword"; 
  return this.http.put(url,form,{responseType:'text'})
}

  logOut(): void {
    this.loggedIn.next(false);
    this.router.navigate(['/login']);
    this.data=null;
    sessionStorage.clear();
  }


}
