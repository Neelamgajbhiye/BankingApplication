package com.xoriant.banking.api.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xoriant.banking.api.DTO.DepositWithdrawAccountDataEntry;
import com.xoriant.banking.api.DTO.EditAccountDataEntry;
import com.xoriant.banking.api.DTO.EditCustomerCommand;
import com.xoriant.banking.api.DTO.EditCustomerDataEntry;
import com.xoriant.banking.api.DTO.GetManagerBalanceCommand;
import com.xoriant.banking.api.DTO.RegisterAccountCommand;
import com.xoriant.banking.api.DTO.RegisterCustomerCommand;
import com.xoriant.banking.api.exception.AccountNotFoundException;
import com.xoriant.banking.api.exception.EmptyInputException;
import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.exception.NotSufficientBalanceException;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.Address;
import com.xoriant.banking.api.model.Branch;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Gender;
import com.xoriant.banking.api.model.Manager;
import com.xoriant.banking.api.model.Role;
import com.xoriant.banking.api.model.Status;
import com.xoriant.banking.api.model.User;
import com.xoriant.banking.api.repository.CustomerDao;
import com.xoriant.banking.api.repository.RoleDao;
import com.xoriant.banking.api.repository.UserDao;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerDao customerDAO;
	
	@Autowired
	private RoleDao roleDAO;
	
	@Autowired
	private UserDao userDAO;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@Autowired
//	private RoleDao roleDAO;

	
	
	
	@Override
	public Customer getCustomer(int personId) {
		return customerDAO.findById(personId).get();
	}

	@Override
	public Customer saveCustomer(RegisterCustomerCommand cmd,Manager mainmanager) {
		Customer customer=new Customer();
		customer.setManager(mainmanager);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Date date;
		try {
			date = df.parse(cmd.getDateOfBirth());
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);

			customer.setDob(cal);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		customer.setEmail(cmd.getEmail());
		customer.setGender(Gender.valueOf(cmd.getGender().toUpperCase()));
		customer.setPersonName(cmd.getName());
		customer.setTelephoneNumber(cmd.getPhone());
		User user=new User();
//		Role role=new Role();
//		//role.setRoleId(2);
//		role.setRoleName("customer");
		user.setRole(roleDAO.findById(2).get());
		user.setUsername(cmd.getUserName());
		user.setPassword(passwordEncoder.encode(cmd.getPassword()));
		customer.setUser(user);
		Address address=new Address();
		address.setFlatNo(Integer.valueOf(cmd.getFlatNumber()));
		address.setCity(cmd.getCity());
		address.setState(cmd.getState());
		address.setPincode(cmd.getPincode());
		customer.setAddress(address);
		Branch branch=new Branch();
		branch.setBranchName(mainmanager.getBranch().getBranchName());
		branch.setIFSC(mainmanager.getBranch().getIFSC());
		customer.setBranch(branch);
		customer.setCustomerStatus(Status.ACTIVE);
		//userDAO.save(user);
		customerDAO.save(customer);
		return customer;
	}

	@Override
	public long getAllCustomers(RegisterAccountCommand cmd) {
		List<Customer> customers=customerDAO.findAll();
		for(Customer customer:customers)
		{
			if(customer.getPersonId()==cmd.getCustomerId() && customer.getCustomerStatus().equals(Status.ACTIVE))
			{
//				System.out.println(cmd.getAccountType()+cmd.getInitialDeposit());
				if(cmd.getAccountType().equalsIgnoreCase("SAVING") && cmd.getInitialDeposit()<2000)
				{
					throw new NotSufficientBalanceException("606", "initial deposit must be atleast 2000");
				}
				else {	
				Account account= accountService.create(cmd,customer);
				if(customer.getAccount()==null)
				{
					List<Account> accounts=new ArrayList<>();
					accounts.add(account);
					customer.setAccount(accounts);
				}
				else {
					List<Account> accounts=customer.getAccount();
					accounts.add(account);
					customer.setAccount(accounts);
				}
				customerDAO.save(customer);
				return account.getAccountNumber();
			}
		}
		}
		throw new NotFoundException("601", "user not found");
	}

	@Override
	public double getBalanceByManager(Manager manager,long accountNumber) {
		List<Customer> customers= customerDAO.getCustomerByMangerId(manager.getPersonId());
		if(customers.isEmpty())
		{
			throw new NotFoundException("601","user not found");
		}
		else
		{
			for(Customer customer:customers)
			{
				//System.out.println("customerId"+customer.getPersonId());
				if(customer.getCustomerStatus().equals(Status.ACTIVE))
				{
				List<Account> accounts= accountService.getAccountsByCustomer(customer);
				if(!accounts.isEmpty())
				{
					for(Account account: accounts)
					{
						//System.out.println("account id"+account.getAccountNumber());
						if(account.getAccountNumber()==accountNumber)
						{
							if(account.getAccountStatus().equals(Status.INACTIVE))
							{
								throw new AccountNotFoundException("605", "Account is Deactivated");
							}
							else
							{
								return account.getAccountBalance();
							}
						}
					}
				}
			}
			}
		}
		 throw new AccountNotFoundException("605", "Account not found");
	}

	@Override
	public String doDepositByManager(Manager manager, DepositWithdrawAccountDataEntry cmd) {
		List<Customer> customers= customerDAO.getCustomerByMangerId(manager.getPersonId());
		if(cmd==null)
		{
			throw new EmptyInputException("600","input field empty");
		}
		if(customers.isEmpty())
		{
			throw new NotFoundException("601", "user not found");
		}
		else
		{
			for(Customer customer:customers)
			{
				//System.out.println("customerId"+customer.getPersonId());
				if(customer.getCustomerStatus().equals(Status.ACTIVE))
				{
				List<Account> accounts= accountService.getAccountsByCustomer(customer);
				if(!accounts.isEmpty())
				{
					for(Account account: accounts)
					{
						//System.out.println("account id"+account.getAccountNumber());
						if(account.getAccountNumber()==cmd.getAccountNumber().longValue())
						{
							if(account.getAccountStatus().equals(Status.INACTIVE))
							{
								throw new AccountNotFoundException("605", "Account is Deactivated");
							}
							else
							{
								double balance=account.getAccountBalance();
								account.setAccountBalance(balance+cmd.getAmount());
								accountService.saveAccount(account);
								return "deposit of "+cmd.getAmount()+" is successfull from account "+cmd.getAccountNumber();
							}
						}
						
					}
				}
			}
			}
		}
		throw new AccountNotFoundException("605","account not found");
		
	}

	@Override
	public String doWithdrawByManager(Manager manager, DepositWithdrawAccountDataEntry cmd) {
		List<Customer> customers= customerDAO.getCustomerByMangerId(manager.getPersonId());
		if(customers.isEmpty())
		{
			throw new NotFoundException("601", "user not found");
		}
		else
		{
			for(Customer customer:customers)
			{
				//System.out.println("customerId"+customer.getPersonId());
				if(customer.getCustomerStatus().equals(Status.ACTIVE))
				{
				List<Account> accounts= accountService.getAccountsByCustomer(customer);
				if(!accounts.isEmpty())
				{
					for(Account account: accounts)
					{
						//System.out.println("account id"+account.getAccountNumber());
						if(account.getAccountNumber()==cmd.getAccountNumber())
						{
							if(account.getAccountStatus().equals(Status.INACTIVE))
							{
								throw new AccountNotFoundException("605", "Account is Deactivated");
							}
							else
							{
								double balance=account.getAccountBalance();
								if(balance-cmd.getAmount()>=account.getMinimumBalance())
								{
									account.setAccountBalance(balance-cmd.getAmount());
									accountService.saveAccount(account);
									return "withdraw of "+cmd.getAmount()+" is successfull from account "+cmd.getAccountNumber();
								}
								else
								{
									throw new NotSufficientBalanceException("606","Insufficient balance");
								}
							}
						}
					}
				}
			}
			}
		}
		throw new AccountNotFoundException("605","Account not found");
	}

	@Override
	public int getCustomerIdForManger(Manager manager, int personId) {
		List<Customer> customers=customerDAO.getCustomerByMangerId(manager.getPersonId());
		if(!customers.isEmpty())
		{
		for(Customer customer:customers)
		{
			if(customer.getPersonId()==personId)
			{
				return customer.getPersonId();
			}
		}
		}
	 throw new NotFoundException("601","user does not exist under this manager");
	}

	@Override
	public Customer saveCustomerByCustomerId(Customer customer, EditCustomerCommand cmd) {
		//Customer customer=customerDAO.getById(cust.getPersonId());
		System.out.println(customer);
		DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Date date;
		try {
			date = df.parse(cmd.getDateOfBirth());
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);

			customer.setDob(cal);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		customer.setEmail(cmd.getEmail());
		customer.setGender(Gender.valueOf(cmd.getGender()));
		customer.setPersonName(cmd.getName());
		customer.setTelephoneNumber(cmd.getPhone());
		User user=customer.getUser();
		System.out.println(user.getUserId());
//		Role role=new Role();
//		//role.setRoleId(2);
//		role.setRoleName("customer");
//		user.setRole(role);
		user.setUsername(cmd.getUserName());
		//user.setPassword(user.getPassword());
		customer.setUser(user);
		Address address=customer.getAddress();
		address.setFlatNo(Integer.valueOf(cmd.getFlatNumber()));
		address.setCity(cmd.getCity());
		address.setState(cmd.getState());
		address.setPincode(cmd.getPincode());
		customer.setAddress(address);
		System.out.println(user);
		System.out.println(customer);
		userDAO.save(user);
		customerDAO.save(customer);
		//customerDAO.updateCustomer(customer.getPersonId(),cmd);
		return customer;
		
	}

	@Override
	public long getAccountNumberByManagerId(Manager manager,long accountNumber) {
		List<Customer> customers= customerDAO.getCustomerByMangerId(manager.getPersonId());
//		long accountNumber1=0L;
//		System.out.println(accountNumber+"getaccountbymanagercustomerservice");
//		//customers.forEach(cust->System.out.println( cust.getPersonId()));
		if(customers.isEmpty())
		{
			throw new NotFoundException("605","no user under this manager");
		}
		else
		{
			for(Customer customer:customers)
			{
				//System.out.println("customerId"+customer.getPersonId());
				if(customer.getCustomerStatus().equals(Status.ACTIVE))
				{
				List<Account> accounts= accountService.getAccountsByCustomer(customer);
				if(!accounts.isEmpty())
				{System.out.println("inside accounts not empty");
					for(Account account: accounts)
					{
						
						if(account.getAccountNumber()==accountNumber)
						{
							
							if(account.getAccountStatus().equals(Status.INACTIVE))
							{
								System.out.println("inside inactive condition"+account.getAccountNumber());
								throw new AccountNotFoundException("605", "account is already deletd");
							}
							else
							{
//								System.out.println("inside elde "+account.getAccountNumber());
//								accountNumber1= account.getAccountNumber();
//								System.out.println(accountNumber1+"accnum1");
								return account.getAccountNumber();
							}
						}
					}
				}
				
			}
			}
		}
		throw new AccountNotFoundException("605","Account not found");
	}

	@Override
	public void deleteCustomer(int personId) {
		Customer customer=getCustomer(personId);
		customer.setCustomerStatus(Status.INACTIVE);
		customerDAO.save(customer);
	}

	@Override
	public String doDepositByCustomer(Customer customer, DepositWithdrawAccountDataEntry cmd) {
		//List<Account> accounts= accountService.getAccountsByCustomer(customer);
		
				if(customer.getCustomerStatus().equals(Status.ACTIVE))
				{
				List<Account> accounts= accountService.getAccountsByCustomer(customer);
				if(!accounts.isEmpty())
				{
					for(Account account: accounts)
					{
						//System.out.println("account id"+account.getAccountNumber());
						if(account.getAccountNumber()==cmd.getAccountNumber().longValue())
						{
							if(account.getAccountStatus().equals(Status.INACTIVE))
							{
								throw new AccountNotFoundException("605", "Account is Deactivated");
							}
							else
							{
								double balance=account.getAccountBalance();
								account.setAccountBalance(balance+cmd.getAmount());
								accountService.saveAccount(account);
								return "deposit of "+cmd.getAmount()+" is successfull from account "+cmd.getAccountNumber();
							}
						}
						
					}
				}
			}
			
		
		throw new NotFoundException("601","user is inactive");
		
	}

	@Override
	public String doWithdrawByCustomer(Customer customer, DepositWithdrawAccountDataEntry cmd) {
		if(customer.getCustomerStatus().equals(Status.ACTIVE))
		{
		List<Account> accounts= accountService.getAccountsByCustomer(customer);
		if(!accounts.isEmpty())
		{
			for(Account account: accounts)
			{
				//System.out.println("account id"+account.getAccountNumber());
				if(account.getAccountNumber()==cmd.getAccountNumber())
				{
					if(account.getAccountStatus().equals(Status.INACTIVE))
					{
						throw new AccountNotFoundException("605", "Account is Deactivated");
					}
					else
					{
						double balance=account.getAccountBalance();
						if(balance-cmd.getAmount()>=account.getMinimumBalance())
						{
							account.setAccountBalance(balance-cmd.getAmount());
							accountService.saveAccount(account);
							return "withdraw of "+cmd.getAmount()+" is successfull from account "+cmd.getAccountNumber();
						}
						else
						{
							throw new NotSufficientBalanceException("606","Insufficient balance");
						}
					}
				}
			}
		}
	}

throw new NotFoundException("601","user is inactive");
	}

	@Override
	public boolean isCustomerLoggedIn(Customer customer) {
		if(customer!=null)
		{
			return true;
		}
		return false;
	}
	




}
