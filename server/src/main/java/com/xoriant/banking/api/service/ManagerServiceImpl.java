package com.xoriant.banking.api.service;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.exception.NotSufficientBalanceException;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.Address;
import com.xoriant.banking.api.model.Branch;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Manager;
import com.xoriant.banking.api.model.PersonalInfo;
import com.xoriant.banking.api.model.Status;
import com.xoriant.banking.api.model.TransactionStatus;
import com.xoriant.banking.api.model.Transactions;
import com.xoriant.banking.api.model.User;
import com.xoriant.banking.api.repository.AddressDao;
import com.xoriant.banking.api.repository.BranchDao;
import com.xoriant.banking.api.repository.CustomerDao;
import com.xoriant.banking.api.repository.ManagerDao;
import com.xoriant.banking.api.repository.PersonalInfoDao;
import com.xoriant.banking.api.repository.RoleDao;
import com.xoriant.banking.api.repository.TransactionDao;
import com.xoriant.banking.api.repository.UserDao;
@Transactional
@Service
public class ManagerServiceImpl implements ManagerService{
	@Autowired
	private ManagerDao managerDAO;
	
	@Autowired
	private RoleDao roleDAO;
	
	@Autowired
	private AccountService accountService;

	@Override
	public Manager getManager(Integer personId) {
		Manager manager= managerDAO.findById(personId).get();
		return manager;
		
	}

	@Override
	public Manager saveCustomerForManager(Manager manager, Customer customer) {
		if(manager.getCustomer()==null)
		{
			List<Customer> customers=new ArrayList<Customer>();
			customers.add(customer);
			manager.setCustomer(customers);
			managerDAO.save(manager);

		}
		else
		{
			List<Customer> customers=manager.getCustomer();
			customers.add(customer);
			manager.setCustomer(customers);
			managerDAO.save(manager);
		}
		return manager;
	}

	@Override
	public boolean isManagerLoggedIn(Manager manager) {
		if(manager!=null)
		{
			return true;
		}
		return false;	}

	@Override
	public boolean isAccountUnderManager(Manager manager, long accountNumber) {

		List<Customer> customers=manager.getCustomer();
		
for(Customer customer:customers)
{
	List<Account> accounts=customer.getAccount();
	
	for(Account account:accounts)
	{
		if(account.getAccountNumber()==accountNumber)
		{
			return true;
		}
	}
}
	return false;
	}

//	@Autowired
//	 CustomerDao customerDao;
//	
//	@Autowired
//	 ManagerDao managerDao;
//	
//	@Autowired
//	 UserDao userDao;
//	
//	@Autowired
//	AccountService accountService;
//	
//	@Autowired
//	TransactionDao transactionDao;
	
//	@Override
//	public void addCustomer(Customer customer) {
//		
//		customerDao.save(customer);
//	}
//
//	@Override
//	public Customer customerFindById(Integer custId) {
//		System.out.println(customerDao.findByPersonId(custId));
//		return customerDao.findByPersonId(custId);
//	}
//
//	@Override
//	public void addNewAccount(Account account) {
//		accountService.addNewAccount(account);
//		
//		
//	}
//
//	@Override
//	public double balanceEnquiry(long accountNumber) {
//		// TODO Auto-generated method stub
//		return accountService.getAccountbyId(accountNumber).getAccountBalance();
//	}
//
//	@Override
//	public void deposit(long accountNumber, double amount, String desc) {
//		Account account = accountService.getAccountbyId(accountNumber);
//		Transactions trx1 = new Transactions();
//		trx1.setAmount(amount);
//		trx1.setDateOfTransaction(new GregorianCalendar());
//		trx1.setFromAccount(null);
//		trx1.setToAccount(account);
//		trx1.setDescription(desc);
//		trx1.setTransactionStatus(TransactionStatus.SUCCESS);
//		transactionDao.save(trx1);
//		List<Transactions> transactions = account.getTransaction();
//		transactions.add(trx1);
//		account.setTransaction(transactions);
//		accountService.updateAccountBalance(account.getAccountNumber(),account.getAccountBalance() + amount);
//		
//	}
//
//	@Override
//	public void withdraw(long accountNumber, double amount, String desc) throws NotSufficientBalanceException {
//		Account account = accountService.getAccountbyId(accountNumber);
//		Transactions trx1 = new Transactions();
//		trx1.setAmount(amount);
//		trx1.setDateOfTransaction(new GregorianCalendar());
//		trx1.setFromAccount(account); 
//		trx1.setToAccount(null);
//		trx1.setDescription(desc);
//		double balance = account.getAccountBalance();
//		if (balance < amount) {
//			trx1.setTransactionStatus(TransactionStatus.FAILED);
//			transactionDao.save(trx1);
//			throw new NotSufficientBalanceException();
//		} else {
//			
//			trx1.setTransactionStatus(TransactionStatus.SUCCESS);
//			transactionDao.save(trx1);
//			accountService.updateAccountBalance(account.getAccountNumber(),account.getAccountBalance() - amount);
//		}
//		
//		List<Transactions> transactions = account.getTransaction();
//		transactions.add(trx1);
//		account.setTransaction(transactions);
//	}
//
//	
//	@Override
//	public void deleteCustomer(Integer personId) {
//		customerDao.findByPersonId(personId).setCustomerStatus(Status.INACTIVE);
//		List<Account> accounts=customerDao.findByPersonId(personId).getAccount();
//		for(Account acc:accounts)
//		{
//			acc.setAccountStatus(Status.INACTIVE);
//		}
//		
//	}
//
//	@Override
//	public void deleteAccount(long accountNumber) {
//		accountService.getAccountbyId(accountNumber).setAccountStatus(Status.INACTIVE);
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void fundTransfer(long fromAccountNumber, long toAccountNumber,double amount,String desc) throws NotSufficientBalanceException {
//		// TODO Auto-generated method stub
//		Account fromAccount = accountService.getAccountbyId(fromAccountNumber);
//		Account toAccount = accountService.getAccountbyId(toAccountNumber);
//		Transactions trx1 = new Transactions();
//		trx1.setAmount(amount);
//		trx1.setDateOfTransaction(new GregorianCalendar());
//		trx1.setFromAccount(fromAccount);
//		trx1.setToAccount(toAccount);
//		trx1.setDescription(desc);
//		double balance = fromAccount.getAccountBalance();
//		if (balance < amount)
//			try {
//				{
//					trx1.setTransactionStatus(TransactionStatus.FAILED);
//					transactionDao.save(trx1);
//					throw new NotSufficientBalanceException();
//				}
//			} catch (NotSufficientBalanceException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		else {
//			
//			trx1.setTransactionStatus(TransactionStatus.SUCCESS);
//			transactionDao.save(trx1);
//			accountService.updateAccountBalance(fromAccount.getAccountNumber(),fromAccount.getAccountBalance() - amount);
//			accountService.updateAccountBalance(toAccount.getAccountNumber(),toAccount.getAccountBalance() + amount);
//		}
//		
//		List<Transactions> fromAccountTransactions = fromAccount.getTransaction();
//		fromAccountTransactions.add(trx1);
//		fromAccount.setTransaction(fromAccountTransactions);
//		
//		List<Transactions> transactions = fromAccount.getTransaction();
//		transactions.add(trx1);
//		fromAccount.setTransaction(transactions);
//		
//	}
//	
//	
//	
//	@Override
//	public List<Transactions> getTransactionbyPersonId(Integer personId) {
//		// TODO Auto-generated method stub
//		Customer customer=customerFindById(personId);
//		List<Account> accounts=customer.getAccount();
//		List<Transactions> transactions=new ArrayList<Transactions>();
//		for(Account acc:accounts)
//		{
//			transactions.addAll(acc.getTransaction());
//		}
//		
//		return transactions;
//	}
//
//	@Override
//	public Manager getManagerById(Integer managerId) {
//		// TODO Auto-generated method stub
//		return managerDao.findById(managerId).get() ;
//	}
//	
//	@Override
//	public PersonalInfo login(String loginName, String password) throws NotFoundException {
//		User user = userDao.authenticateUser(loginName, password);
//		Manager manager = managerDao.findById(user.getUserId()).get();
//
//		if (manager != null)
//			return manager;
//		return null;
//	}

}
