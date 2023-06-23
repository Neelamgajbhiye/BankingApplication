
import { Injectable } from '@angular/core';
import { ValidatorFn, AbstractControl, AsyncValidatorFn } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { map } from 'rxjs';
import { ManagerService } from '../manager-service/manager.service';

@Injectable({
  providedIn: 'root'
})
export class CustomvalidationService {
  
 
  constructor(private managerService: ManagerService){}

  patternValidator(): ValidatorFn {
    return (control: AbstractControl): { [key: string]: any }|null => {
      if (!control.value) {
        return null;
      }
      const regex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$');
      const valid = regex.test(control.value);
      return valid ? null : { invalidPassword: true };
    };
  }

  MatchPassword(password: string, confirmPassword: string) {
    return (formGroup: FormGroup) => {
      const passwordControl = formGroup.controls[password];
      const confirmPasswordControl = formGroup.controls[confirmPassword];

      if (!passwordControl || !confirmPasswordControl) {
        return null;
      }

      if (confirmPasswordControl.errors && !confirmPasswordControl.errors['passwordMismatch']) {
        return null;
      }

      if (passwordControl.value !== confirmPasswordControl.value) {
        confirmPasswordControl.setErrors({ passwordMismatch: true });
      } else {
        confirmPasswordControl.setErrors(null);
        
      }
      return null;
    }
  }

   userNameValidator(managerService:ManagerService): AsyncValidatorFn {
    return (control: AbstractControl): {[key: string]: boolean} | any => {
      return new Promise(resolve =>
        managerService.isUsernameTaken(control.value).subscribe(res => {
            if (res == 'true') {
                resolve({ usernameNotAvailable:true });
            }
            else {
                resolve(null);
            }
        }));
 
   }
   }


   dateOfBirthValidator() : ValidatorFn {
    return (control: AbstractControl): { [key: string]: any }|null => {
      if (!control.value) {
        return {null:null};
      }
     //console.log(control.value);
     
      return new Date(control.value)>new Date() ? {futureDateError:true}:null;
    };
  }
  
  emailValidator(): ValidatorFn{
    return (control: AbstractControl): { [key: string]: any }|null => {
      if (!control.value) {
        return null;
      }
      //let valid2=true;
     // let valid=true;
      //if (control.value != null && control.value !== '') {
        const regex = new RegExp('[A-Za-z0-9._%+-]{1,}@[a-zA-Z]{1,}([.]{1}[a-zA-Z]{2,}|[.]{1}[a-zA-Z]{2,}[.]{1}[a-zA-Z]{2,})');
         //valid2 = new RegExp('/^[_a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/').test(control.value);
     const valid=regex.test(control.value);
      //  } 
     // const valid2 = new RegExp('/^[_a-z0-9]+(\.[_a-z0-9]+)*@[a-z0-9-]+(\.[a-z0-9-]+)*(\.[a-z]{2,4})$/').test(control.value);
      return valid ? null : { invalidEmail: true };
    };
}

phoneValidator(): ValidatorFn{
  return(control:AbstractControl): { [key: string]: any }|null => {
    if(!control.value){
      return null;
    }

    if(control.value.length!==10){
      return {invalidPhone:true};
    }

    return null;
  }
}
}