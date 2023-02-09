package com.xoriant.banking.api.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xoriant.banking.api.exception.AccountNotFoundException;
import com.xoriant.banking.api.exception.EmptyInputException;
import com.xoriant.banking.api.exception.NewPasswordAndOldPasswordSameException;
import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.exception.NotSufficientBalanceException;
import com.xoriant.banking.api.exception.PasswordMismatchException;
import com.xoriant.banking.api.exception.UserNotFoundException;

@ControllerAdvice
public class MyControllerAdvice {
	
	@ExceptionHandler(EmptyInputException.class)
	public ResponseEntity<String> handleEmptyInput(EmptyInputException emptyInputException){
		return new ResponseEntity<String>(emptyInputException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseEntity<String> handlePasswordMismatch(PasswordMismatchException passwordMismatchException){
		return new ResponseEntity<String>(passwordMismatchException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFound(UserNotFoundException userNotFoundException){
		return new ResponseEntity<String>(userNotFoundException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<String> handleNotFound(NotFoundException notFoundException){
		return new ResponseEntity<String>(notFoundException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	

	@ExceptionHandler(AccountNotFoundException.class)
	public ResponseEntity<String> handleAccountNotFound(AccountNotFoundException accountNotFoundException){
		return new ResponseEntity<String>(accountNotFoundException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotSufficientBalanceException.class)
	public ResponseEntity<String> handleInsufficientBalance(NotSufficientBalanceException notSufficientBalanceException){
		return new ResponseEntity<String>(notSufficientBalanceException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(NewPasswordAndOldPasswordSameException.class)
	public ResponseEntity<String> handleNewPasswordAndOldPasswordSame(NewPasswordAndOldPasswordSameException notSufficientBalanceException){
		return new ResponseEntity<String>(notSufficientBalanceException.getErrorMessage(),HttpStatus.BAD_REQUEST);
	}
}
