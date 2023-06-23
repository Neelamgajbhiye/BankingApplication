import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {
  errorMessage = 'Invalid Credentials';
  successMessage: string="Password changed Successfully";
  invalidLogin = false;
  loginSuccess = false;
  isEditable = false;
  accountNumber!: number;
  Title='Chan';
  account!:any;
  urlPath!: string;
  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
  }

  changePassword(form:any): void {
    this.loginService.changePassword(form).subscribe(resp=>{
      this.successMessage=resp;
      this.loginSuccess = true;
     this.invalidLogin = false;
    },(error)=>{
      this.errorMessage=error.message;
      this.invalidLogin = true;
      this.loginSuccess=false;
    });
  }
  

}
