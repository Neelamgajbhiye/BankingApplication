package com.xoriant.banking;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.doubleThat;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.xoriant.banking.api.DTO.*;
import com.xoriant.banking.api.model.*;
import com.xoriant.banking.api.repository.AccountDao;
import com.xoriant.banking.api.repository.UserDao;
import com.xoriant.banking.api.service.AccountService;
import com.xoriant.banking.api.service.AccountServiceImpl;
import com.xoriant.banking.api.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	private MockMvc mockMvc;
	
	ObjectMapper objectMapper=new ObjectMapper();
	ObjectWriter objectWriter=objectMapper.writer();
	
	@Mock
	private com.xoriant.banking.api.repository.AccountDao accountDao;
	
	@InjectMocks
	private AccountServiceImpl accountService;
	
	@org.junit.Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
//		this.mockMvc=MockMvcBuilders.standaloneSetup(accountService).build();
	}
	
	@Test
	public void getBalanceByCustomer_TakeAccountDataEntryCommandAndCustomerData_ReturnaDouble() throws Exception{
		Customer customer=new Customer();
		customer.setPersonId(3);
		
		EditAccountDataEntry cmd=new EditAccountDataEntry();
		cmd.setAccountNumber(2);
		
		Account account=new Account();
		account.setAccountNumber(2);
		account.setAccountBalance(5000.0);
		account.setAccountStatus(Status.ACTIVE);
		
		Account account2=new Account();
		account2.setAccountNumber(3);
		account2.setAccountBalance(6000.0);
		account2.setAccountStatus(Status.INACTIVE);
		
		List<Account> accounts=new ArrayList<Account>();
		accounts.add(account);
		accounts.add(account2);
		
		Mockito.when(accountDao.getAccountByCustomerId(any(Integer.class))).thenReturn(accounts);
		
		double result=accountService.getBalanceByCustomer(customer, cmd.getAccountNumber());
		Assert.assertEquals(5000.0, result, 0);
		
	}
}