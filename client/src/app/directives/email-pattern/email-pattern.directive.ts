import { Directive } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, Validator } from '@angular/forms';
import { CustomvalidationService } from 'src/app/services/customvalidation-service/customvalidation.service';

@Directive({   
  selector: '[appEmailPattern]',
  providers: [{ provide: NG_VALIDATORS, useExisting:EmailPatternDirective, multi: true }]
})
export class EmailPatternDirective implements Validator {

  constructor(private customValidator: CustomvalidationService) { }

  validate(control: AbstractControl): { [key: string]: any } | null {
    return this.customValidator.emailValidator()(control);
  }
}