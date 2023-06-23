package com.xoriant.banking.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xoriant.banking.api.model.Manager;
import com.xoriant.banking.api.model.User;
import com.xoriant.banking.api.repository.ManagerDao;
import com.xoriant.banking.api.repository.UserDao;
import com.xoriant.banking.api.service.ManagerService;
import com.xoriant.banking.api.service.UserService;


@RestController
//@RequestMapping("/api/v1")
public class AdminController {
//	@Autowired
//	private UserService userRepo;
//	
//	@Autowired
//	private ManagerService managerDao;
//	
//	@Autowired
//	private BCryptPasswordEncoder passwordEncoder;
//	
//	@PostMapping("/admin")
//	public User addUserByAdmin(@RequestBody User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		System.out.println(user);
//		userRepo(user);
//		
//		return user;
//	}
//	
//	@PostMapping("/manager")
//	public String addManager(@RequestBody Manager manager) {
//		managerDao.save(manager);
//		
//		return "manager added succesfully";
//	}
//
//	
//	@GetMapping("/manager/{personId}")
//	public Manager getManager(@PathVariable int personId) {
//		//managerDao.findById(personId).get();
//		
//		return managerDao.findById(personId).get();
//}
	}
