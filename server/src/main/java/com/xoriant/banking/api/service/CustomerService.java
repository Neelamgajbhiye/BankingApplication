package com.xoriant.banking.api.service;

import com.xoriant.banking.api.DTO.DepositWithdrawAccountDataEntry;
import com.xoriant.banking.api.DTO.EditAccountDataEntry;
import com.xoriant.banking.api.DTO.EditCustomerCommand;
import com.xoriant.banking.api.DTO.EditCustomerDataEntry;
import com.xoriant.banking.api.DTO.GetManagerBalanceCommand;
import com.xoriant.banking.api.DTO.RegisterAccountCommand;
import com.xoriant.banking.api.DTO.RegisterCustomerCommand;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Manager;

public interface CustomerService {
	
	public Customer saveCustomer(RegisterCustomerCommand cmd,Manager manager);
	/*
	 * List of all the customer under that manager which is logged in 
	 */
	public Customer getCustomer(int personId) ;
	/*
	 * update or edit the customer data  
	 */
//	public void editCustomer(Customer customer);
//	/*
//	 * finding the customer by its customerId 
//	 */
//	public Customer getCustomerByCustomerId(Integer customerId);
	/*
	 * making customer inaccessible by and manager  
	 */
     public void deleteCustomer(int personId);
//	/*
//	 * update or edit the password of the customer   
//	 */
//	public void changePassword(Customer customer);
	/*
	 * getting all the customer in the database
	 */
	public long getAllCustomers(RegisterAccountCommand cmd) ;
	
	public double getBalanceByManager(Manager manager,long accountNumber);
	public String doDepositByManager(Manager manager,DepositWithdrawAccountDataEntry cmd);
	public String doWithdrawByManager(Manager manager,DepositWithdrawAccountDataEntry cmd);
	public int getCustomerIdForManger(Manager manager,int personId);
	public Customer saveCustomerByCustomerId(Customer customer,EditCustomerCommand cmd);
	public long getAccountNumberByManagerId(Manager manager,long accountNumber);
	public boolean isCustomerLoggedIn(Customer customer);
	public String doDepositByCustomer(Customer customer,DepositWithdrawAccountDataEntry cmd);
	public String doWithdrawByCustomer(Customer customer,DepositWithdrawAccountDataEntry cmd);

}
