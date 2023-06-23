package com.xoriant.banking.api.command;

/**
 * FundTransferCommand is command to use to  transfer fund
 * @author Purohit_h
 *
 */
@SuppressWarnings("deprecation")
public class FundTransferCommand {
//	@Min(value=1, message="fill all the fields")  
   private int toAccount;
	//@Min(value=1, message="fill all the fields")  
	private int fromAccount;
	// @DecimalMin("1.0") 
	private double amount;
	//@NotBlank(message = "Fill all the fields")
	String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getToAccount() {
		return toAccount;
	}
	public void setToAccount(long toAccount) {
		this.toAccount = (int) toAccount;
	}
	public long getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(long fromAccount) {
		this.fromAccount = (int) fromAccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
