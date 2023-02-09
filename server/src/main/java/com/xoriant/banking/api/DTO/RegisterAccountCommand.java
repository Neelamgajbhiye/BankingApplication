package com.xoriant.banking.api.DTO;
/*
 * save the customerId,initial deposit and pass it to the controller which we get from UI/form of Create Account
 */
public class RegisterAccountCommand {

	private Integer customerId;
	private Integer initialDeposit;
	private String accountType;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getInitialDeposit() {
		return initialDeposit;
	}
	public void setInitialDeposit(Integer initialDeposit) {
		this.initialDeposit = initialDeposit;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	@Override
	public String toString() {
		return "RegisterAccountCommand [customerId=" + customerId + ", initialDeposit=" + initialDeposit
				+ ", accountType=" + accountType + "]";
	}
	
}
