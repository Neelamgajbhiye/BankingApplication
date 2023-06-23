import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { TokenStorageService } from 'src/app/services/token-storage-service/token-storage.service';
import { LoginService } from 'src/app/services/login-service/login.service';
import { ManagerService } from 'src/app/services/manager-service/manager.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
username:any="";
isManager:boolean= false;
  constructor(private router : Router,private managerService:ManagerService,private tokenService:TokenStorageService) { }

  ngOnInit(): void {
   this.username= this.tokenService.getUser().user.username;
   if(this.managerService.isManager()){
    this.isManager=true;
   }
  }
checkBalance():number{
  this.router.navigate(['balanceEnquiry']);

return 0;
}
addCustomer():void {
  this.router.navigate(['addCustomer']);
}
addAccount():void {
  this.router.navigate(['addAccount']);
}
transferFund(){
this.router.navigate(['fundTransfer']);
}
deleteCustomer(){
  this.router.navigate(['deleteCustomer']);
}
  deleteAccount(){ this.router.navigate(['deleteAccount']);}
  deposit(){
    this.router.navigate(['deposit']);
  }
  withdraw(){
    this.router.navigate(['withdraw']);
  }
  miniStatement(){
    this.router.navigate(['miniStatement']);
  }

  updateCustomer(){
    this.router.navigate(['updateCustomer']);
  }
  updateAccount(){ this.router.navigate(['updateAccount']);}

  changePassword(){ this.router.navigate(['changePassword']);

}
}

