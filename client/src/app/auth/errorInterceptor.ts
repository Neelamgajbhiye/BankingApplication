import {Injectable, Injector} from '@angular/core';
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http'

// {AuthorizationService} from "../authorization.service/authorization.service";
import {HttpError} from "../model/HttpError";
import {Router} from "@angular/router";
import { catchError, Observable, retry, throwError } from 'rxjs';
import { BackendError, ErrorSeverity } from '../model/error-message';
//import {Toaster} from "nw-style-guide/toasts";

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
// Regular dep. injection doesn't work in HttpInterceptor due to a framework issue (as of angular@5.2.9),
// use Injector directly (don't forget to add @Injectable() decorator to class).
constructor(private _injector: Injector) {}
    // intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    //     throw new Error('Method not implemented.');
    // }

intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const logFormat = 'background: maroon; color: white';

    return next.handle(req).pipe(
retry(1),catchError((err)=>{
  
    let error!:BackendError;
        console.log(err);
        
        if (err instanceof ErrorEvent) {
          // this is client side error
          error = this.handleUnknownError();
        //  alert('Client error: ' + err.error.message);
        } else {
         
          // this is server side error
          error = this.handleBackendError( err);
          //alert('Server error with code: ' + err.status);
        }
        return throwError(() => error);
    // let errorMessage ='';
    // if(error.error instanceof ErrorEvent)
    // {
    //     console.log("this is client side error");
    //    // alert(error.error);
    //     errorMessage = `Error:${error.error.message}`;
    // }
    // else{
    //     console.log('this is server side error');
    //    // alert(error.status+" error.message");
    //     errorMessage=`Error Code:${error.status},Message:${error.message}`;
    // }
    // switch (error.status) {
    //     case 0:console.error("no response from server"); errorMessage="no response from server"; break; 

    //     case HttpError.BadRequest:
    //         console.error('%c Bad Request 400', logFormat);
    //         //throw error;
    //         console.log(error);
    //         //return throwError(error);
    //         break;

    //     case HttpError.Unauthorized:
    //         console.error('%c Unauthorized 401', logFormat);
    //         throw error;
    //         window.location.href = '/login' + window.location.hash;
    //         break;

    //     case HttpError.NotFound:
    //         //show error toast message
    //         console.error('%c Not Found 404', logFormat);
    //         throw error;
    //         // const _toaster = this._injector.get(Toaster),
    //         //     _router = this._injector.get(Router);
    //         // _toaster.show({
    //         //     message: exception.error && exception.error.message ? exception.error.message :
    //         //         exception.statusText,
    //         //     typeId: 'error',
    //         //     isDismissable: true
    //         // });
    //         //_router.navigate(['']);
    //         break;

    //     case HttpError.TimeOut:
    //         // Handled in AnalyticsExceptionHandler
    //         throw error;
    //         console.error('%c TimeOut 408', logFormat);
    //         break;

    //     case HttpError.Forbidden:
    //         console.error('%c Forbidden 403', logFormat);
    //         throw error;
    //         // const _authService = this._injector.get(AuthorizationService);
    //         // _authService.showForbiddenModal();
    //         break;

    //     case HttpError.InternalServerError:
    //         console.error('%c big bad 500', logFormat);
    //         throw error;
    //         break;
    //     }
    //    throw error;
      // throw new Error(errorMessage)
       //new Error("error");
}))    

} private handleUnknownError(): BackendError {
    // this is not from backend. Format our own message.
    return {
      message: 'Unknown error!',
      severity: ErrorSeverity.FATAL,
      code: 'UNKNOWN_ERROR',
    };
  }

  private handleBackendError(err:any): BackendError {
    switch (err.status) {
      case 0:
        return {
        title: err.error?.status || 'Default title',
        message:'No response from server ',
        severity: ErrorSeverity.ERROR,
        code: err.error?.identifierCode
          ? err.error.identifierCode
          : err.status,
      }; break; 

      case HttpError.BadRequest:
        return {
          title: err.error?.status || 'Bad Request',
          message:
          err.error && err.error.message
            ? err.error.message
            : err.error
            ? err.error
            : err.message
            ? err.error:'Bad request from server',
          severity: ErrorSeverity.ERROR,
          code: err.error?.identifierCode
            ? err.error.identifierCode
            : err.status,
        };
          break;

      case HttpError.Unauthorized:
        return {
          title: err.error?.status || 'Unauthorized',
          message:'Thid request in Unauthorized',
          severity: ErrorSeverity.ERROR,
          code: err.error?.identifierCode
            ? err.error.identifierCode
            : err.status,
        };
          break;

      case HttpError.NotFound:
        return {
          title: err.error?.status || 'Default title',
          message:'Server error:Not Found',
          severity: ErrorSeverity.ERROR,
          code: err.error?.identifierCode
            ? err.error.identifierCode
            : err.status,
        };
          break;

      case HttpError.TimeOut:
        return {
          title: err.error?.status || 'Default title',
          message:
            err.error && err.error.message
              ? err.error.message
              : err.error
              ? err.error
              : err.message,
          severity: ErrorSeverity.ERROR,
          code: err.error?.identifierCode
            ? err.error.identifierCode
            : err.status,
        };
          break;

      case HttpError.Forbidden:
        return {
          title: err.error?.status || 'Default title',
          message:'Server error Forbidden with status code ' + err.status,
          severity: ErrorSeverity.ERROR,
          code: err.error?.identifierCode
            ? err.error.identifierCode
            : err.status,
        };
          break;

      case HttpError.InternalServerError:
        return {
          title: err.error?.status || 'Default title',
          message:'Internal Server Error with status code ' + err.status,
          severity: ErrorSeverity.ERROR,
          code: err.error?.identifierCode
            ? err.error.identifierCode
            : err.status,
        };
          break;
      }
    // // Backend returned error, format it here
    return {
      title: err.error?.status || 'Default title',
      message:
        err.error && err.error.message
          ? err.error.message
          : err.error
          ? err.error
          : err.message,
      severity: ErrorSeverity.ERROR,
      code: err.error?.identifierCode
        ? err.error.identifierCode
        : err.status,
    };
  }
}
