import { Component, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login-service/login.service';
import { User } from 'src/app/model/User';
import { TokenStorageService } from 'src/app/services/token-storage-service/token-storage.service';
import { NgForm } from '@angular/forms';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user:User=new User("","");
  result!:any;
  errorMessage = 'Invalid Credentials';
  successMessage: string="";
  invalidLogin = false;
  loginSuccess = false;
  fieldTextType: boolean=false;
  @ViewChild('loginForm') ngForm!: NgForm;
  constructor(private loginService : LoginService,private tokenStorage: TokenStorageService ,private router : Router) { }

  ngOnInit(): void {
   // this.router.navigate(['/login']); 
  }
  // form = new FormGroup({  
  //   email : new FormControl('' , Validators.required),  
  //   password : new FormControl('' , Validators.required)  
  // });  
  handleLogin(form:any)  
  {  
        this.user.username = form.username;  
        this.user.password = form.password;  
   //console.log(form.username);
  //console.log(this.user);
  //this.authService.login(form);
      this.loginService.login(this.user).subscribe(  response => {  
        this.result =  response;
       this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      this.tokenStorage.saveToken(this.result.jwtToken);
      this.tokenStorage.saveUser(this.result.personalInfo);
    // sessionStorage.setItem("role",this.result.personalInfo.user.role.roleName);
    // sessionStorage.setItem("personId",this.result.personalInfo.personId);
    //   sessionStorage.setItem("username",this.result.personalInfo.personName);
    //   sessionStorage.setItem("userInfo",JSON.stringify(this.result));
      this.router.navigateByUrl('/dashboard');
    }, (error) => {
      console.log(error);
      this.errorMessage=error.message;
      this.invalidLogin = true;
      this.loginSuccess = false;
      //this.toastr.success('Hello world!', 'Toastr fun!');
    });      
}
toggleFieldTextType() {
  this.fieldTextType = !this.fieldTextType;
}

}
