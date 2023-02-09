package com.xoriant.banking.api.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.banking.api.DTO.FundTranferCommand;
import com.xoriant.banking.api.exception.AccountNotFoundException;
import com.xoriant.banking.api.exception.EmptyInputException;
import com.xoriant.banking.api.exception.NotSufficientBalanceException;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Status;
import com.xoriant.banking.api.model.TransactionStatus;
import com.xoriant.banking.api.model.Transactions;
import com.xoriant.banking.api.repository.TransactionDao;

@Service
public class TransactionServiceImpl implements TransactionService{

	
	@Autowired
	private TransactionDao transactionDao;
	
	@Autowired
	private AccountService accountService;
	
	@Override
	public String doFundTransfer(FundTranferCommand cmd) {
		Account accountFrom=accountService.getAccountById(cmd.getFromAccount());
		Account accountTo=accountService.getAccountById(cmd.getToAccount());
		if(accountFrom ==null || accountTo==null)
		{
			throw new AccountNotFoundException("605","Account does not exist");
		}
		else
		{
			if(accountFrom.getAccountStatus().equals(Status.INACTIVE) ||accountTo.getAccountStatus().equals(Status.INACTIVE))
			{
				throw new AccountNotFoundException("605","One of the Account is Inactive");
			}
			else {
				if(accountFrom.getAccountBalance()-cmd.getAmount()<accountFrom.getMinimumBalance())
				{
					throw new NotSufficientBalanceException("606","insuficient balance");
				}
				else {
					accountFrom.setAccountBalance(accountFrom.getAccountBalance()-cmd.getAmount());
					accountTo.setAccountBalance(accountTo.getAccountBalance()+cmd.getAmount());
					
					Transactions transactions=new Transactions();
					transactions.setAmount(cmd.getAmount());
					transactions.setDateOfTransaction(Calendar.getInstance());
					transactions.setFromAccount(accountFrom);
					transactions.setToAccount(accountTo);
					transactions.setTransactionStatus(TransactionStatus.SUCCESS);
					transactions.setDescription("from account "+accountFrom.getAccountNumber()+"to account "+accountTo.getAccountNumber()+"amount of "+cmd.getAmount());
					transactionDao.save(transactions);
					if(accountTo.getTransaction()==null)
					{
						List<Transactions> tList=new ArrayList<>();
						tList.add(transactions);
						accountTo.setTransaction(tList);
					}
					else
					{
						List<Transactions> tList=accountTo.getTransaction();
						tList.add(transactions);
						accountTo.setTransaction(tList);
					}
					if(accountFrom.getTransaction()==null)
					{
						List<Transactions> tList=new ArrayList<>();
						tList.add(transactions);
						accountFrom.setTransaction(tList);
					}
					else
					{
						List<Transactions> tList=accountFrom.getTransaction();
						tList.add(transactions);
						accountFrom.setTransaction(tList);
					}
					accountService.saveAccount(accountTo);
					accountService.saveAccount(accountFrom);
					return "Fund transfer of "+cmd.getAmount()+" from account "+cmd.getFromAccount()+" to account "+cmd.getToAccount()+" successfull";
				}
			}
		}
	}

	@Override
	public List<Transactions> getTransactions(long fromAccount) {
		
		List<Transactions> transactions=transactionDao.findAllTransactionByAccountNumber(fromAccount);
		if(transactions.isEmpty())
		{
			throw new EmptyInputException("601","no transactions done");
		}
		
		return transactions;
		
	}

	@Override
	public String doFundTransferCustomer(Customer customer, FundTranferCommand cmd) {
		List<Account> accounts= accountService.getAccountsByCustomer(customer);
		if(accounts.isEmpty())
		{
			throw new AccountNotFoundException("607","No account exist for this user");
		}
		else
		{
			Account accountFrom=null;
			Account accountTo=accountService.getAccountById(cmd.getToAccount());
			for(Account account:accounts)
			{
				if(account.getAccountNumber()==cmd.getFromAccount())
				{
					accountFrom=account;
				}
			}
			if(accountFrom ==null || accountTo==null)
			{
				throw new AccountNotFoundException("605","Account does not exist");
			}
			else
			{
				if(accountFrom.getAccountStatus().equals(Status.INACTIVE) ||accountTo.getAccountStatus().equals(Status.INACTIVE))
				{
					throw new AccountNotFoundException("605","One of the Account is Inactive");
				}
				else {
					if(accountFrom.getAccountBalance()-cmd.getAmount()<accountFrom.getMinimumBalance())
					{
						throw new NotSufficientBalanceException("606","insuffiecient balance");
					}
					else {
						accountFrom.setAccountBalance(accountFrom.getAccountBalance()-cmd.getAmount());
						accountTo.setAccountBalance(accountTo.getAccountBalance()+cmd.getAmount());
						
						Transactions transactions=new Transactions();
						transactions.setAmount(cmd.getAmount());
						transactions.setDateOfTransaction(Calendar.getInstance());
						transactions.setFromAccount(accountFrom);
						transactions.setToAccount(accountTo);
						transactions.setTransactionStatus(TransactionStatus.SUCCESS);
						transactions.setDescription("from account "+accountFrom.getAccountNumber()+"to account "+accountTo.getAccountNumber()+"amount of "+cmd.getAmount());
						transactionDao.save(transactions);
						if(accountTo.getTransaction()==null)
						{
							List<Transactions> tList=new ArrayList<>();
							tList.add(transactions);
							accountTo.setTransaction(tList);
						}
						else
						{
							List<Transactions> tList=accountTo.getTransaction();
							tList.add(transactions);
							accountTo.setTransaction(tList);
						}
						if(accountFrom.getTransaction()==null)
						{
							List<Transactions> tList=new ArrayList<>();
							tList.add(transactions);
							accountFrom.setTransaction(tList);
						}
						else
						{
							List<Transactions> tList=accountFrom.getTransaction();
							tList.add(transactions);
							accountFrom.setTransaction(tList);
						}
						accountService.saveAccount(accountTo);
						accountService.saveAccount(accountFrom);
						return "Fund transfer of "+cmd.getAmount()+" from account "+cmd.getFromAccount()+" to account "+cmd.getToAccount()+" successfull";
					}
				}
			}
			
		}
	}

}
