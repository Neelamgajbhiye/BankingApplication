package com.xoriant.banking.api.command;

import java.sql.Date;

/**
 * CustomizedStatementCommand is command to use customized statement
 * @author Purohit_h
 *
 */
public class CustomizedStatementCommand {


	private long accountNo;
	private double amountLowerLimit;
	private int numberOfTransaction; 
	private Date toDate;
	private Date fromDate;

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}


	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	

	public double getAmountLowerLimit() {
		return amountLowerLimit;
	}

	public void setAmountLowerLimit(double amountLowerLimit) {
		this.amountLowerLimit = amountLowerLimit;
	}

	public int getNumberOfTransaction() {
		return numberOfTransaction;
	}

	public void setNumberOfTransaction(int numberOfTransaction) {
		this.numberOfTransaction = numberOfTransaction;
	}

	
}
