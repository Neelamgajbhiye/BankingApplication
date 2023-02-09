import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditAccountComponent } from './edit-account.component';

describe('EditAccountComponent', () => {
  let component: EditAccountComponent;
  let fixture: ComponentFixture<EditAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    fixture.whenStable().then( () => {
      console.log(component.accountForm.controls['accountNumber'])
      //component.accountForm.controls['name'].setValue('test_venue');
   })
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
