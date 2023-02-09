package com.xoriant.banking.api.service;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.xoriant.banking.api.DTO.EditAccountDataEntry;
import com.xoriant.banking.api.DTO.RegisterAccountCommand;
import com.xoriant.banking.api.exception.AccountNotFoundException;
import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.exception.NotSufficientBalanceException;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.AccountType;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.PersonalInfo;
import com.xoriant.banking.api.model.Status;
import com.xoriant.banking.api.model.Transactions;
import com.xoriant.banking.api.repository.AccountDao;
import com.xoriant.banking.api.repository.CustomerDao;
import com.xoriant.banking.api.repository.PersonalInfoDao;
import com.xoriant.banking.api.repository.TransactionDao;

@Transactional
@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountDao accountDao;

	@Autowired
	TransactionDao transactionDao;

	@Override
	public void addNewAccount(Account account) {

		accountDao.save(account);

	}

	@Override
	public Account getAccountbyId(long accountnumber) {
		// TODO Auto-generated method stub
		return accountDao.findById(accountnumber).get();
	}

	@Override
	public void updateAccountBalance(long accountNumber, double amount) {
		accountDao.updateAccountBalance(accountNumber, amount);

	}

	@Override
	public void addTransaction(Transactions trx, long accountNumber) {
		// TODO Auto-generated method stub
		transactionDao.save(trx);
		getAccountbyId(accountNumber).getTransaction().add(trx);

	}

	@Override
	public List<Transactions> getTransactionbyAccountNumber(long accountNumber) {
		// TODO Auto-generated method stub
		return getAccountbyId(accountNumber).getTransaction();
	}

	@Override
	public boolean authAccount(long accountNumber) {
		// if(accountDao.existsById(accountNumber))
		System.out.println(accountNumber + "authaccount");
		accountDao.findAll().forEach(acc -> System.out.println(acc.getAccountNumber()));
		Account account = accountDao.getById(accountNumber);
		System.out.println(account.getAccountNumber());
		if (account == null)
			return false;
		else
			return true;
	}

	// kp code

	@Override
	public Account create(RegisterAccountCommand cmd, Customer customer) {
		Account account = new Account();
		account.setAccountBalance(cmd.getInitialDeposit());
		account.setAccountType(AccountType.valueOf(cmd.getAccountType()));
		account.setAccountStatus(Status.ACTIVE);
		Calendar calendar = Calendar.getInstance();
		account.setDate(calendar);
		calendar.add(Calendar.YEAR, 3);
		account.setDateOfClosure(calendar);
		account.setDescription("creation of account for customer " + customer.getPersonName());
		if (cmd.getAccountType().equals("SAVING")) {
			account.setMinimumBalance(2000);
		} else {
			account.setMinimumBalance(0);
		}
		account.setCustomer(customer);
		accountDao.save(account);
		return account;
	}

	@Override
	public void deleteAccount(long accountNumber) {
		// add condition if accountNumber does not exist
		System.out.println("accountservice deleteaccount" + accountNumber);
//		if (!authAccount(accountNumber)) {
//			throw new AccountNotFoundException("605", "Account does not exist");
//		}
		Account account = accountDao.findById(accountNumber).get();
		account.setAccountStatus(Status.INACTIVE);
		accountDao.save(account);

	}

	@Override
	public List<Account> getAccountsByCustomer(Customer customer) {
		// add if no accounts are present conditon
		List<Account> accounts = accountDao.getAccountByCustomerId(customer.getPersonId());
		return accounts;
	}

	@Override
	public double getBalanceByCustomer(Customer customer, long accountNumber) {
		if (customer == null) {
			throw new NotFoundException("601", "user not found");
		} else {
			List<Account> accounts = accountDao.getAccountByCustomerId(customer.getPersonId());

			
			if (!accounts.isEmpty()) {
				return accounts.stream()
				.filter(acc -> (acc.getAccountNumber() == accountNumber)
						&& (acc.getAccountStatus().equals(Status.ACTIVE)))
				.map(acc->acc.getAccountBalance()).findAny().orElseThrow(() -> new AccountNotFoundException("605", "Account is Deactivated"));
//				for (Account account : accounts) {
//					// System.out.println("account id"+account.getAccountNumber());
//					if (account.getAccountNumber() == accountNumber) {
//						if (account.getAccountStatus().equals(Status.INACTIVE)) {
//							throw new AccountNotFoundException("605", "Account is Deactivated");
//						} else {
//							return account.getAccountBalance();
//						}
//					}
//				}
			}
			throw new AccountNotFoundException("605", "Account does not exist for the user");
		}

	}

	@Override
	public void saveAccount(Account account) {
		// TODO Auto-generated method stub
		accountDao.save(account);
	}

	@Override
	public Account getAccountById(long accountNumber) {
		if (accountDao.findById(accountNumber).isEmpty()) {
			throw new AccountNotFoundException("605", "Account does not exist.");
		}

		else {

			if (accountDao.findById(accountNumber).get().getAccountStatus().equals(Status.INACTIVE)) {
				throw new AccountNotFoundException("605", "Account is Deactivated");
			}
			return accountDao.findById(accountNumber).get();
		}
	}

	@Override
	public Account editAccount(Account account, RegisterAccountCommand cmd) {
		// TODO Auto-generated method stub
		if (account.getAccountBalance() < 2000 & cmd.getAccountType().equalsIgnoreCase("saving")) {
			throw new NotSufficientBalanceException("610",
					"For changing account to saving account minimum balance should be atleast 2000");
		} else {
			account.setAccountType(AccountType.valueOf(cmd.getAccountType()));
		}

		return account;
	}

	@Override
	public long doMiniStatementForCustomer(Customer customer, long accountNumber) {
		Account account;
		if (accountDao.findById(accountNumber).isEmpty()) {
			throw new AccountNotFoundException("605", "Account does not exist for the user");
			// return -4;
		} else {
			account = accountDao.findById(accountNumber).get();
		}
		if (account.getCustomer().getPersonId() != customer.getPersonId()) {
			throw new NotFoundException("605", "Account does not exist for the user");
		} else {
			if (account.getAccountStatus().equals(Status.INACTIVE)) {
				throw new AccountNotFoundException("605", "Account is Deactivated");
			} else {
				return account.getAccountNumber();
			}
		}
	}

}
