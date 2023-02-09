import { HttpClient, HttpHandler } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { LoginService } from 'src/app/services/login-service/login.service';

import { LoginComponent } from './login.component';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers:[LoginService,HttpClient,HttpHandler]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    fixture.whenStable().then( () => {
         console.log(component.ngForm.controls['username'])
        // component.ngForm.controls['venue_name].setValue('test_venue');
      })
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});


// fixture = TestBed.createComponent(TournamentEditVenueComponent);
// comp = fixture.componentInstance;
// fixture.detectChanges();

// fixture.whenStable().then( () => {
//    console.log(comp.ngForm.controls['venue_name'])
//    component.ngForm.controls['venue_name].setValue('test_venue');
// })
