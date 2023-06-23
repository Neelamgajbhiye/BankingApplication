package com.xoriant.banking.api.service;

import java.util.List;

import com.xoriant.banking.api.DTO.CustomStatmentCommand;
import com.xoriant.banking.api.DTO.FundTranferCommand;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Manager;
import com.xoriant.banking.api.model.Transactions;

public interface TransactionService {

	public String doFundTransfer( com.xoriant.banking.api.DTO.FundTranferCommand cmd);
	public String doFundTransferCustomer(Customer customer, FundTranferCommand cmd);
//	/*
//	 * saving the transaction of the user as it does the fund transfer
//	 */
//	public void saveTransaction(Transactions transaction);
	/*
	 * getting all the transaction data from the a account number
	 */
	public List<Transactions> getTransactions(long fromAccount);
	/*
//	 * getting all the customized transaction data 
//	 */
//	public List<Transactions> getCustomTransactions(CustomStatmentCommand cmd);
}
