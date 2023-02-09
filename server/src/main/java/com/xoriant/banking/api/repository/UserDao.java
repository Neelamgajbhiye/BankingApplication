package com.xoriant.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xoriant.banking.api.exception.NotFoundException;
import com.xoriant.banking.api.model.User;

public interface UserDao extends JpaRepository<User,Integer> {
	
	
	@Query("from User u where u.username=:username and u.password=:password")
	public User authenticateUser(@Param("username") String userName,@Param("password") String password);
	
	
	@Query(" select u.password from User u where u.username=:username")
	public String getPasswordByUsername(@Param("username") String userName);
	
	@Query(" from User u where u.username=:username")
	public User getByUsername(@Param("username") String userName);

}
