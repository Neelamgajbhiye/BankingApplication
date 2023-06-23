package com.xoriant.banking.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.banking.api.DTO.AddCustomerDTO;
import com.xoriant.banking.api.DTO.loginInfo;
import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.model.Customer;
import com.xoriant.banking.api.model.Manager;
import com.xoriant.banking.api.model.PersonalInfo;
import com.xoriant.banking.api.model.User;
import com.xoriant.banking.api.repository.PersonalInfoDao;
import com.xoriant.banking.api.service.ManagerService;
import com.xoriant.banking.api.service.PersonalInfoService;
import com.xoriant.banking.api.service.UserService;
import com.xoriant.banking.api.service.userServiceImpl;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/mapping")
public class UserController {

	@Autowired
	 UserService userService;
	
	@Autowired
	ManagerService managerService;
	
	@Autowired
	PersonalInfoService personalInfoService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody loginInfo user)  {
		
		System.out.println(user.getUsername());
			User loggedInUser = userService.authUser(user.getUsername(), user.getPassword());
			PersonalInfo personalInfo=personalInfoService.getByUserId(loggedInUser.getUserId());
//			AddCustomerDTO userinfo=new AddCustomerDTO();
//			userinfo.setPersonId(personalInfo.getPersonId());
//			userinfo.setGender(personalInfo.getGender());
//			userinfo.setCity(personalInfo.getAddress().getCity());
//			userinfo.setPersonName(personalInfo.getPersonName());
//			userinfo.setPassword(loggedInUser.getPassword());
//			userinfo.setUsername(loggedInUser.getUsername());
			
			
//		if(loggedInUser.getRole().getRoleName().equals("manager"))
//		{
//			Manager manager=managerService.getManagerById(personalInfo.getPersonId());
//			return new ResponseEntity<Manager>(manager,HttpStatus.OK);
//			
//		}
//		else {
//			Customer customer=managerService.customerFindById(personalInfo.getPersonId());
//			return new ResponseEntity<Customer>(customer,HttpStatus.OK);
//		}
			
			return new ResponseEntity<PersonalInfo>(personalInfo,HttpStatus.OK);
		
	}
	
	
}
