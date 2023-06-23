import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/services/login-service/login.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
username:string='';
userInfo:any;
  constructor(private loginService:LoginService) { }

  ngOnInit(): void {
//this.loginServices
if(this.loginService.isLoggedIn)
{
  this.userInfo=JSON.parse(sessionStorage.getItem('auth-user')!);
  //console.log(this.userInfo);
  this.username=this.userInfo.personName;
}
else{
alert("User not logged in!");
}

  }

}
