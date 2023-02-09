import { Directive, forwardRef } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, Validator } from '@angular/forms';
import { CustomvalidationService } from 'src/app/services/customvalidation-service/customvalidation.service';

@Directive({
  selector: '[appValidateDateofbirth]',
  providers: [{ provide: NG_VALIDATORS, useExisting: 
    ValidateDateofbirthDirective , multi: true }]
})
export class 
ValidateDateofbirthDirective  implements Validator {

  constructor(private customValidator: CustomvalidationService) { }

  validate(control: AbstractControl): { [key: string]: any } | null {
    return this.customValidator.dateOfBirthValidator()(control);
  }
}



