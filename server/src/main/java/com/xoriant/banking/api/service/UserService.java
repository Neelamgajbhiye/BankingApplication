package com.xoriant.banking.api.service;

import org.springframework.data.repository.query.Param;

import com.xoriant.banking.api.DTO.ChangePasswordDTO;
import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.model.PersonalInfo;
import com.xoriant.banking.api.model.User;

public interface UserService {

	User authUser(String userName, String password) ;
	
	String getPasswordByUsername(String userName) ;

	String getRolebyId(int userId);
	
	PersonalInfo getPersonalInfo(int userId); 
	
	boolean getByUsername(String userName);
	
	String changePassword(User user,ChangePasswordDTO cmd);
	//User findbyUserName


}
