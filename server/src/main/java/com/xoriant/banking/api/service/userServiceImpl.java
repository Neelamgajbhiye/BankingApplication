package com.xoriant.banking.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xoriant.banking.api.DTO.ChangePasswordDTO;
import com.xoriant.banking.api.exception.EmptyInputException;
import com.xoriant.banking.api.exception.NewPasswordAndOldPasswordSameException;
import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.exception.PasswordMismatchException;
import com.xoriant.banking.api.exception.UserNotFoundException;
import com.xoriant.banking.api.model.PersonalInfo;
import com.xoriant.banking.api.model.User;
import com.xoriant.banking.api.repository.PersonalInfoDao;
import com.xoriant.banking.api.repository.UserDao;
@Transactional
@Service("userService")
public class userServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PersonalInfoDao personalInfoDao;
	
	@Override
	public User authUser(String userName, String password)  {
		User user=null;
		if(userName=="" || password=="")
		{
			throw new EmptyInputException("601","Input fields are empty");

		}
		else if(getByUsername(userName)==false)
		{
			System.out.println("here");
			throw new UserNotFoundException("600","Username does not exist");
		}
			else {
				
				if(!password.equals(getPasswordByUsername(userName)))
				{
					throw new PasswordMismatchException("602","Password does not match");
				}
				 user = userDao.authenticateUser(userName, password);
			}
		
		
	 
		
		//System.out.println(user);
		return user;
	}
	
	@Override
	public String getRolebyId(int userId) {
		// TODO Auto-generated method stub
		return userDao.findById(userId).get().getRole().getRoleName();
	}

	@Override
	public PersonalInfo getPersonalInfo(int userId) {
		// TODO Auto-generated method stub
		return personalInfoDao.findByUserId(userId);
	}

	@Override
	public String getPasswordByUsername(String userName) {
		// TODO Auto-generated method stub
		return userDao.getPasswordByUsername(userName);
	}

	@Override
	public boolean getByUsername(String userName) {
		// TODO Auto-generated method stub
		User user =userDao.getByUsername(userName);
		System.out.println(user);
		if (user==null)
		{
			return false;
		}
		return true;
	}

	@Override
	public String changePassword(User user, ChangePasswordDTO cmd) {
		if(!cmd.getNewPassword().equals(cmd.getConfirmPassword()))
		{
			throw new PasswordMismatchException("610","new Password and confirm password are not same");
		}
		else {
			if(user.getPassword().equals(cmd.getConfirmPassword()))
			{
				throw new NewPasswordAndOldPasswordSameException("611", "new Password cant be same as old password");
			}
			else {
				user.setPassword(cmd.getConfirmPassword());
				userDao.save(user);
				return "password changed Succesfully";
			}
		}
		
		
		// TODO Auto-generated method stub
		
	}

}
