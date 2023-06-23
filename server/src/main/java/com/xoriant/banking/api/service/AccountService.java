package com.xoriant.banking.api.service;

import java.util.List;

import com.xoriant.banking.api.DTO.EditAccountDataEntry;
import com.xoriant.banking.api.DTO.RegisterAccountCommand;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Transactions;


public interface AccountService {
	
	
	
	 void addNewAccount(Account account) ;
	 Account getAccountbyId(long accountNumber) ;
	void updateAccountBalance(long accountNumber, double d);
	//Transaction func in transaction service
	void addTransaction(Transactions trx,long accountNumber);
	
	List<Transactions> getTransactionbyAccountNumber(long accountNumber);
	boolean authAccount(long accountNumber);
	
	

	public Account create(RegisterAccountCommand cmd,Customer customer);

	public void deleteAccount(long accountNumber);

	public List<Account> getAccountsByCustomer(Customer customer);
	public double getBalanceByCustomer(Customer customer,long accountNumber);
	public void saveAccount(Account account);
	public Account getAccountById(long accountNumber);
	public Account editAccount(Account account,RegisterAccountCommand cmd);
	public long doMiniStatementForCustomer(Customer customer,long accountNumber);
	
}
