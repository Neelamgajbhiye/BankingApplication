package com.xoriant.banking.api.DTO;

/*
 * model for getting the accountNumber from check balance form
 */
public class GetManagerBalanceCommand {

	private Integer accountNumber;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	
}
