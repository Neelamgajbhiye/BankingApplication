package com.xoriant.banking.api.DTO;

/*
 * Model for Custom Statement page 
 */
public class CustomStatmentCommand {

	private Integer accountNumber;
	private String fromDate;
	private String toDate;
	private Integer lowerAmount;
	private Integer numberOftransactions;
	public Integer getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(Integer accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public Integer getLowerAmount() {
		return lowerAmount;
	}
	public void setLowerAmount(Integer lowerAmount) {
		this.lowerAmount = lowerAmount;
	}
	public Integer getNumberOftransactions() {
		return numberOftransactions;
	}
	public void setNumberOftransactions(Integer numberOftransactions) {
		this.numberOftransactions = numberOftransactions;
	}
	@Override
	public String toString() {
		return "CustomStatmentCommand [accountNumber=" + accountNumber + ", fromDate=" + fromDate + ", toDate=" + toDate
				+ ", lowerAmount=" + lowerAmount + ", numberOftransactions=" + numberOftransactions + "]";
	}
}
