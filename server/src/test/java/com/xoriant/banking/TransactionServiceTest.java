package com.xoriant.banking;


import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.xoriant.banking.api.DTO.FundTranferCommand;
import com.xoriant.banking.api.exception.AccountNotFoundException;
import com.xoriant.banking.api.exception.NotSufficientBalanceException;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.Status;
import com.xoriant.banking.api.repository.TransactionDao;
import com.xoriant.banking.api.service.AccountService;
import com.xoriant.banking.api.service.TransactionServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class TransactionServiceTest {

	//private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private AccountService accountService;

	@Mock
	private TransactionDao transactionDao;

	@InjectMocks
	private TransactionServiceImpl transactionService;

	@org.junit.Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
			//this.mockMvc=MockMvcBuilders.standaloneSetup(accountService).build();
	}

	@Test
	public void doFundTransfer_TakeFundTransferCommandData_ReturnADoubleScenerio1() throws Exception {

		FundTranferCommand cmd = new FundTranferCommand();
		cmd.setFromAccount(111132);
		cmd.setToAccount(111118);
		cmd.setAmount(5000);

		Account fromAccount = new Account();
		fromAccount.setAccountNumber(111132);
		fromAccount.setAccountBalance(8000);
		fromAccount.setAccountStatus(Status.ACTIVE);
		fromAccount.setMinimumBalance(2000);

		Account toAccount = new Account();
		toAccount.setAccountNumber(111118);
		toAccount.setAccountBalance(3000);
		toAccount.setAccountStatus(Status.ACTIVE);

		Mockito.when(accountService.getAccountById(111132)).thenReturn(fromAccount);
		Mockito.when(accountService.getAccountById(111118)).thenReturn(toAccount);
		Mockito.doNothing().when(accountService).saveAccount(toAccount);
		Mockito.doNothing().when(accountService).saveAccount(fromAccount);

		String result = transactionService.doFundTransfer(cmd);
		Assert.assertEquals("Fund transfer of "+cmd.getAmount()+" from account "+cmd.getFromAccount()+" to account "+cmd.getToAccount()+" successfull", result);

	}

	@Test(expected= NotSufficientBalanceException.class) 
	public void doFundTransfer_TakeFundTransferCommandData_ReturnADoubleScenerio2() throws Exception {

		FundTranferCommand cmd = new FundTranferCommand();
		cmd.setFromAccount(111132);
		cmd.setToAccount(111118);
		cmd.setAmount(7000);

		Account fromAccount = new Account();
		fromAccount.setAccountNumber(111132);
		fromAccount.setAccountBalance(8000);
		fromAccount.setAccountStatus(Status.ACTIVE);
		fromAccount.setMinimumBalance(2000);

		Account toAccount = new Account();
		toAccount.setAccountNumber(111118);
		toAccount.setAccountBalance(3000);
		toAccount.setAccountStatus(Status.ACTIVE);

		Mockito.when(accountService.getAccountById(111132)).thenReturn(fromAccount);
		Mockito.when(accountService.getAccountById(111118)).thenReturn(toAccount);

		String result = transactionService.doFundTransfer(cmd);
		NotSufficientBalanceException thrown = assertThrows(
			      NotSufficientBalanceException.class,()->transactionService.doFundTransfer(cmd));
		Assert.assertEquals("insuficient balance",thrown.getErrorMessage());

	}

	@Test(expected= AccountNotFoundException.class) 
	public void doFundTransfer_TakeFundTransferCommandData_ReturnADoubleScenerio3() throws Exception {

		FundTranferCommand cmd = new FundTranferCommand();
		cmd.setFromAccount(111132);
		cmd.setToAccount(111118);
		cmd.setAmount(7000);

		Account fromAccount = new Account();
		fromAccount.setAccountNumber(111132);
		fromAccount.setAccountBalance(8000);
		fromAccount.setAccountStatus(Status.INACTIVE);
		fromAccount.setMinimumBalance(2000);

		Account toAccount = new Account();
		toAccount.setAccountNumber(111118);
		toAccount.setAccountBalance(3000);
		toAccount.setAccountStatus(Status.ACTIVE);

		Mockito.when(accountService.getAccountById(111132)).thenReturn(fromAccount);
		Mockito.when(accountService.getAccountById(111118)).thenReturn(toAccount);

		String result = transactionService.doFundTransfer(cmd);
		Assert.assertEquals("One of the Account is Inactive", result, 0);

	}

	@Test(expected= AccountNotFoundException.class) 
	public void doFundTransfer_TakeFundTransferCommandData_ReturnADoubleScenerio4() throws Exception {

		FundTranferCommand cmd = new FundTranferCommand();
		cmd.setFromAccount(111132);
		cmd.setToAccount(1111138);
		cmd.setAmount(7000);

		Account fromAccount = null;

		Account toAccount = new Account();
		toAccount.setAccountNumber(111118);
		toAccount.setAccountBalance(3000);
		toAccount.setAccountStatus(Status.ACTIVE);

		Mockito.when(accountService.getAccountById(111132)).thenReturn(fromAccount);
		Mockito.when(accountService.getAccountById(1111138)).thenReturn(toAccount);

		String result = transactionService.doFundTransfer(cmd);
		Assert.assertEquals("Account does not exist", result, 0);

	}

}