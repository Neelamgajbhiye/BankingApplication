package com.xoriant.banking.api.DTO;

public class EditAccountDataEntry {

	/*
	 * To save the account number that we get enter account number form or UI used from mini statement,balance
	 * Enquire and Edit Account. 
	 */
	private Integer accountNumber;

	public Integer getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public String toString() {
		return "EditAccountDataEntry [accountNumber=" + accountNumber + "]";
	}
	
}
