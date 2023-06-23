package com.xoriant.banking;

import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.xoriant.banking.api.DTO.RegisterAccountCommand;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Status;
import com.xoriant.banking.api.repository.AccountDao;
import com.xoriant.banking.api.service.AccountServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

	ObjectMapper objectMapper=new ObjectMapper();
	ObjectWriter objectWriter=objectMapper.writer();
	
	@org.junit.Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Mock
	AccountDao accountDao;
	
	@InjectMocks
	AccountServiceImpl accountService;
	
	@Test
	public void getBalancebyCustomer_givenAccountNumberAndCustomer_returnDoubleBalance(){
		Customer customer=new Customer();
		customer.setPersonId(11);
		
		Account account1=new Account();
		account1.setAccountNumber(111132);
		account1.setAccountBalance(12000);
		account1.setAccountStatus(Status.ACTIVE);
		long accountNumber=111132;
		List<Account> accounts=new ArrayList<Account>();
		accounts.add(account1);
				Mockito.when(accountDao.getAccountByCustomerId(any(Integer.class))).thenReturn(accounts);
				
			double result = accountService.getBalanceByCustomer(customer, accountNumber);
				Assert.assertEquals(result, 12000,0);
	}
	
	@Test
	public void getBalancebyManager_givenAccountNumberAndCustomer_returnDoubleBalance(){
		Customer customer=new Customer();
		customer.setPersonId(11);
		
		Account account1=new Account();
		account1.setAccountNumber(111132);
		account1.setAccountBalance(12000);
		account1.setAccountStatus(Status.ACTIVE);
		long accountNumber=111132;
		List<Account> accounts=new ArrayList<Account>();
		accounts.add(account1);
				Mockito.when(accountDao.getAccountByCustomerId(any(Integer.class))).thenReturn(accounts);
				
			double result = accountService.getBalanceByCustomer(customer, accountNumber);
				Assert.assertEquals(result, 12000,0);
	}
	
	@Test
	public void createAccount_givenRegisterAccountcmdAndCustomer_returnAccount() {
		
	}
	
//
//	public void deleteAccount(long accountNumber);
//
//	public List<Account> getAccountsByCustomer(Customer customer);
//	public double getBalanceByCustomer(Customer customer,long accountNumber);
//	public void saveAccount(Account account);
//	public Account getAccountById(long accountNumber);
//	public Account editAccount(Account account,RegisterAccountCommand cmd);
//	public long doMiniStatementForCustomer(Customer customer,long accountNumber);
	
	
}