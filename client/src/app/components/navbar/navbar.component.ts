import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { LoginService } from 'src/app/services/login-service/login.service';
import { AuthService } from '../../auth/auth.service';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn$!: Observable<boolean>;

  constructor(private loginService: LoginService,private router:Router) { }

  ngOnInit() {
    this.isLoggedIn$ = this.loginService.isLoggedIn;
  }

  onLogout() {
    this.loginService.logOut();
  }

  onDashboard() {
    if(this.loginService.isLoggedIn)
    {
this.router.navigate(['/dashboard']);
    };
  }
  onProfile() {
    this.router.navigate(['/profile']);
  }
   
  
}
