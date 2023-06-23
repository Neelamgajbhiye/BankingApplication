package com.xoriant.banking.api.service;

import java.util.List;

import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.exception.NotSufficientBalanceException;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.Address;
import com.xoriant.banking.api.model.Branch;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Manager;
import com.xoriant.banking.api.model.PersonalInfo;
import com.xoriant.banking.api.model.Transactions;
import com.xoriant.banking.api.model.User;

public interface ManagerService {
	
	
//	Manager getManagerById(Integer managerId);
//	
//	
//	void addCustomer(Customer customer);
//
//	Customer customerFindById(Integer custId);
//	
//	void addNewAccount(Account account);
//	
// 	double balanceEnquiry(long accountNumber);
//	
//	void deposit(long accountNumber,double amount,String desc);
//	
//	void withdraw(long accountNumber,double amount,String desc) throws NotSufficientBalanceException;
//	
//	void deleteCustomer(Integer personId);
//	
//	void deleteAccount(long accountNumber);
//	
//	void fundTransfer(long fromAccountNumber,long toAccountNumber,double amount,String desc) throws NotSufficientBalanceException;
//
//	List<Transactions> getTransactionbyPersonId(Integer personId);
//
//
//	PersonalInfo login(String loginName, String password) throws NotFoundException;
	
	//void deleteCustomer(Customer customer);

	//List<Customer> customersFindByManagerId(int managerId);
	
	
public Manager getManager(Integer personId);
	
public boolean isManagerLoggedIn(Manager manager);
	
	public Manager saveCustomerForManager(Manager manager,Customer customer);
	
	public boolean isAccountUnderManager(Manager manager,long accountNumber);
}
