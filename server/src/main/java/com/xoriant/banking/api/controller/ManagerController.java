package com.xoriant.banking.api.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.banking.api.DTO.ChangePasswordDTO;
import com.xoriant.banking.api.DTO.DepositWithdrawAccountDataEntry;
import com.xoriant.banking.api.DTO.EditAccountDataEntry;
import com.xoriant.banking.api.DTO.EditCustomerCommand;
import com.xoriant.banking.api.DTO.FundTranferCommand;
import com.xoriant.banking.api.DTO.GetManagerBalanceCommand;
import com.xoriant.banking.api.DTO.JwtResponse;
import com.xoriant.banking.api.DTO.RegisterAccountCommand;
import com.xoriant.banking.api.DTO.RegisterCustomerCommand;
import com.xoriant.banking.api.DTO.loginInfo;
import com.xoriant.banking.api.aop.LogAspect;
import com.xoriant.banking.api.exception.AccountNotFoundException;
import com.xoriant.banking.api.exception.EmptyInputException;
import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.exception.NotSufficientBalanceException;
import com.xoriant.banking.api.model.Account;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Manager;
import com.xoriant.banking.api.model.PersonalInfo;
import com.xoriant.banking.api.model.Transactions;
import com.xoriant.banking.api.model.User;
import com.xoriant.banking.api.repository.AccountDao;
import com.xoriant.banking.api.repository.ManagerDao;
import com.xoriant.banking.api.repository.TransactionDao;
import com.xoriant.banking.api.service.AccountService;
import com.xoriant.banking.api.service.CustomerService;
import com.xoriant.banking.api.service.ManagerService;
import com.xoriant.banking.api.service.PersonalInfoService;
import com.xoriant.banking.api.service.TransactionService;
import com.xoriant.banking.api.service.UserService;
import com.xoriant.banking.api.util.JWTUtility;
//http://localhost:4200
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/user")
public class ManagerController {
	// save the customer data to loggedIn user
	Customer customerMain;
	// save the manager data to loggedIn user
	Manager managerMain;
	
	User userMain;
	@Autowired
	LogAspect logAspect;
	// To access the service method of respective model
	@Autowired
	private PersonalInfoService personalInfoService;

	@Autowired
	private UserService userService;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtility jwtToken;
	


//			@Autowired
//			private AddressService addressService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ManagerService managerService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transactionService;

//	done
	@PostMapping("/addcustomer")
	public ResponseEntity<String>  AddCustomer(@RequestBody RegisterCustomerCommand cmd) {
		if(managerService.isManagerLoggedIn(managerMain))
		{
			Customer customer = customerService.saveCustomer(cmd, managerMain);
			System.out.println(cmd);
			Manager manager = managerService.saveCustomerForManager(managerMain, customer);
			managerMain = manager;
		}
		else
		{
			throw new NotFoundException("608","No manager found. please login with manager role");
		}
		
		// managerService.addCustomer(customer);
		return new ResponseEntity<String>("customer added succesfully",HttpStatus.CREATED);

	}
//done
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody  loginInfo user) throws Exception{
		//logAspect.logAroundUserId(user.getUsername());
		System.out.println(user.getUsername());
		User loggedInUser = userService.authUser(user.getUsername(), user.getPassword());
		PersonalInfo personalInfo = personalInfoService.getByUserId(loggedInUser.getUserId());
		try {
			
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
		}
		catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		
		
		
		final UserDetails userDetails=userDetailsService.loadUserByUsername(loggedInUser.getUsername());
		final String jwt=jwtToken.generateToken(userDetails);
		userMain=loggedInUser;
		JwtResponse jwtResponse=new JwtResponse(personalInfo, jwt);
		
		if (loggedInUser.getRole().getRoleName().equals("manager")) {
			managerMain = managerService.getManager(personalInfo.getPersonId());
			// return new ResponseEntity<Manager>(manager,HttpStatus.OK);

		} else {
			customerMain = customerService.getCustomer(personalInfo.getPersonId());
			// return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		}

		return new ResponseEntity<JwtResponse>(jwtResponse, HttpStatus.OK);

	}

	// done
	@PostMapping("/newaccount")
	public ResponseEntity<String> NewAccount(@RequestBody RegisterAccountCommand cmd) {
//		long flag = customerService.getAllCustomers(cmd);
//
//		if (flag == 1) {
			return new ResponseEntity<String>("account added succesfully "+customerService.getAllCustomers(cmd),HttpStatus.CREATED);
//		} else {
//			throw new NotFoundException("601", "user not found");
//		}
//		accountService.addNewAccount(account);
//		return account;
		// return "account added succesfully";
	}

//	@GetMapping("/newaccount/{accountnumber}")
//	public ResponseEntity<Account>  getAccount(@PathVariable long accountnumber) {
//		return new ResponseEntity<Account>(accountService.getAccountbyId(accountnumber),HttpStatus.OK);
//
//	}

//	@GetMapping("/customer/{custId}")
//	public Customer getCustomer(@PathVariable Integer custId) {
//		
//		return managerService.customerFindById(custId);
//		
//	}
	// done
	@GetMapping("/customerbalance/{accountNumber}")
	public ResponseEntity<Double> getBalancebyCustomer(@PathVariable long accountNumber) {

		return new ResponseEntity<Double>(accountService.getBalanceByCustomer(customerMain, accountNumber),HttpStatus.OK);
	}

	// done
	@GetMapping("/managerbalance/{accountNumber}")
	public ResponseEntity<Double> getBalancebyManager(@PathVariable long accountNumber) {
		return new ResponseEntity<Double>( customerService.getBalanceByManager(managerMain, accountNumber),HttpStatus.OK);
	}

	//done
	@PutMapping(value="/depositaccountmanager", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> depositbyManager(@RequestBody   DepositWithdrawAccountDataEntry cmd) {
		
		
		return new ResponseEntity<String>( customerService.doDepositByManager(managerMain,cmd),HttpStatus.OK);
	}
//	done
	@PutMapping("/withdrawaccountmanager")
	public ResponseEntity<String> withdrawbyManager(@RequestBody  DepositWithdrawAccountDataEntry cmd){
		
		//managerService.dowithdraw(accountNumber, amount,desc);
		return  new ResponseEntity<String>(customerService.doWithdrawByManager(managerMain, cmd),HttpStatus.OK);
	}
//	done
	@DeleteMapping("/deleteaccount/{accountNumber}")
	public  ResponseEntity<String> deleteAccount(@PathVariable long accountNumber) {
		//System.out.println(accountNumber);
		long tempVariable= customerService.getAccountNumberByManagerId(managerMain, accountNumber);
		
		//System.out.println(tempVariable+"tenpsbhbjh");
		accountService.deleteAccount(tempVariable);
		return new ResponseEntity<String>( "account "+tempVariable+" deleted successfully",HttpStatus.OK);
	}
	
	//done
	@DeleteMapping("/deletecustomer/{personId}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Integer personId) {
		int tempVariable=customerService.getCustomerIdForManger(managerMain, personId);
		customerService.deleteCustomer(tempVariable);
		//HttpStatus.NO_CONTENT
		return new ResponseEntity<String>("customer "+tempVariable+" deleted successfully",HttpStatus.OK);
	}

	
	//done
	@PutMapping("/fundtransfermanager")
	public ResponseEntity<String> fundTransferByManager(@ RequestBody  FundTranferCommand cmd)
	{
		
			return new ResponseEntity<String>( transactionService.doFundTransfer(cmd),HttpStatus.OK);
	
	}
	//done
	@PutMapping("/fundtransfercustomer")
	public  ResponseEntity<String> fundTransferByCustomer(@ RequestBody  FundTranferCommand cmd)
	{
		
			return new ResponseEntity<String>(transactionService.doFundTransferCustomer(customerMain,cmd),HttpStatus.OK);
	
	}
	//done
	@GetMapping("/ministatementmanager/{accountNumber}")
	public ResponseEntity<List<Transactions>> miniStatementbyManager(@PathVariable long accountNumber){
		//Account account=accountService.getAccountById(accountNumber);
		
			return new ResponseEntity<List<Transactions>>(transactionService.getTransactions(accountNumber),HttpStatus.OK);
		
	}
	
	//done
	@GetMapping("/ministatementcustomer/{accountNumber}")
	public ResponseEntity<List<Transactions>> miniStatementbyCustomer(@PathVariable long accountNumber){
		long tempVariable=accountService.doMiniStatementForCustomer(customerMain, accountNumber);
	
		return new ResponseEntity<List<Transactions>>( transactionService.getTransactions(tempVariable),HttpStatus.OK);
	}
	
	
	@PutMapping("/editcustomer/{personId}")
	public ResponseEntity<Customer> editCustomer(@PathVariable int personId,@RequestBody EditCustomerCommand cmd){
		Customer customer=customerService.getCustomer(personId);
		if(customer==null) {
			throw new NotFoundException("601","user not found");
		}
		
		
		
		return new ResponseEntity<Customer>(customerService.saveCustomerByCustomerId(customer, cmd),HttpStatus.OK);
	}
	
	@GetMapping("/customer/{personId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int personId){
		//Customer customer=customerService.getCustomer(personId);
		
		int customerId=customerService.getCustomerIdForManger(managerMain, personId);
		
		Customer customer=customerService.getCustomer(customerId);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
	}
	

	@GetMapping("/account/{accountNumber}")
	public ResponseEntity<Account> getAccount(@PathVariable long accountNumber){
		//Customer customer=customerService.getCustomer(personId);
		
		long accountNumber1=customerService.getAccountNumberByManagerId(managerMain, accountNumber);
		
		Account account=accountService.getAccountbyId(accountNumber1);
		
		return new ResponseEntity<Account>(account,HttpStatus.OK);
	}
	
	
	@PutMapping("/editaccount/{accountNumber}")
	public ResponseEntity<Account> editAccount(@PathVariable long accountNumber,@RequestBody RegisterAccountCommand cmd){
		Account account=accountService.getAccountbyId(accountNumber);
		if(account==null) {
			throw new AccountNotFoundException("605","account not found");
		}
		
		
		
		return new ResponseEntity<Account>(accountService.editAccount(account, cmd),HttpStatus.OK);
	}
	
	
	
	@PutMapping("/changepassword")
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO cmd) {
		
		return new ResponseEntity<String>(userService.changePassword(userMain, cmd),HttpStatus.OK);
		
	}
	
	@GetMapping("/usernametaken/{userName}")
	public ResponseEntity<Boolean> isUsernameTaken(@PathVariable String userName){
		return new ResponseEntity<Boolean>(userService.getByUsername(userName),HttpStatus.OK);
		
	}
	
//	
//	@PutMapping("/depositaccountcustomer")
//	public  ResponseEntity<String> depositbyCustomer(@RequestBody   DepositWithdrawAccountDataEntry cmd) {
//		
//		
//		return new ResponseEntity<String>(customerService.doDepositByCustomer(customerMain,cmd),HttpStatus.OK);
//	}
////	done
//	@PutMapping("/withdrawaccountcustomerr")
//	public  ResponseEntity<String> withdrawbyCustomer(@RequestBody   DepositWithdrawAccountDataEntry cmd){
//		
//		//managerService.dowithdraw(accountNumber, amount,desc);
//		return new ResponseEntity<String>( customerService.doWithdrawByCustomer(customerMain, cmd),HttpStatus.OK);
//	}

//	
//	@GetMapping("/getManagerDetails/{managerId}")
//	public Manager getManagerDetails(@PathVariable Integer managerId) {
//		return managerService.getManagerById(managerId);
//		
//	}
//	@GetMapping("/customers/{manId}")
//	public List<Customer> getCustomers(@PathVariable Integer manId) {
//		//managerService.addCustomer(customer);
//		
//		
//		return managerService.customersFindByManagerId(manId);
//		
//	}

}
