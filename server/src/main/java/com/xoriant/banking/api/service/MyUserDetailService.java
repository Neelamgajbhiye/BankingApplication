package com.xoriant.banking.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.xoriant.banking.api.repository.UserDao;

@Service
public class MyUserDetailService implements UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.xoriant.banking.api.model.User userdata=userDao.getByUsername(username);
		//System.out.println(userdata.getUsername()+userdata.getPassword());
		return new User(userdata.getUsername(), userdata.getPassword(),new ArrayList<>());
	}

}
