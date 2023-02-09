package com.xoriant.banking.api.command;

/**
 * Deposi is command to use deposit
 * @author Purohit_h
 *
 */
public class DepositCommand {
	
	private long accountNumber;
	private double amount;
	private String description;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long toAccount) {
		this.accountNumber = toAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}	
	
	
