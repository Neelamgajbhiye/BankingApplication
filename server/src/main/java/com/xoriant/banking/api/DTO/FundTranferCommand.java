package com.xoriant.banking.api.DTO;

/*
 * save the account and amount and pass it to the controller which we get from UI/form of Deposit and withdraw
 */
public class FundTranferCommand {

	private Integer fromAccount;
	private Integer toAccount;
	private Integer amount;
	public Integer getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(Integer fromAccount) {
		this.fromAccount = fromAccount;
	}
	public Integer getToAccount() {
		return toAccount;
	}
	public void setToAccount(Integer toAccount) {
		this.toAccount = toAccount;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "FundTranferCommand [fromAccount=" + fromAccount + ", toAccount=" + toAccount + ", amount=" + amount
				+ "]";
	}
	

}
