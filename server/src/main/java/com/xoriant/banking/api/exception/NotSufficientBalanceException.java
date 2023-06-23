package com.xoriant.banking.api.exception;

public class NotSufficientBalanceException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	
	
	
	
	public NotSufficientBalanceException() {
		super();
	}
	public NotSufficientBalanceException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
	}

