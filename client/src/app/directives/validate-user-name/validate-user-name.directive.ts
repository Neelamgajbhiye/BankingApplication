import { Directive, forwardRef } from '@angular/core';
import { Validator, AbstractControl, NG_ASYNC_VALIDATORS, NonNullableFormBuilder } from '@angular/forms';
import { Observable } from 'rxjs';
import { CustomvalidationService } from 'src/app/services/customvalidation-service/customvalidation.service';
import { ManagerService } from 'src/app/services/manager-service/manager.service';

@Directive({
  selector: '[appValidateUserName]',
  providers: [{ provide: NG_ASYNC_VALIDATORS, useExisting: forwardRef(() => ValidateUserNameDirective), multi: true }]

})
export class ValidateUserNameDirective implements Validator {

  constructor(private customValidator: CustomvalidationService,private managerService:ManagerService) { }

  validate(control: AbstractControl): { [key: string]: any } | null {
    return this.customValidator.userNameValidator(this.managerService)(control);
  }
}
