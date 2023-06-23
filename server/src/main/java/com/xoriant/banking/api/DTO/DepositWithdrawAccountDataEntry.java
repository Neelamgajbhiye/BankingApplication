package com.xoriant.banking.api.DTO;

public class DepositWithdrawAccountDataEntry {

	/*
	 * To save the data that we get from withdraw and deposit form or UI
	 */
	private Integer accountNumber;
	private Integer amount;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}


	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "DepositWithdrawAccountDataEntry [accountNumber=" + accountNumber + ", amount=" + amount + "]";
	}
	
}
