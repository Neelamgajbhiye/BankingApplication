package com.xoriant.banking.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.xoriant.banking.api.model.Account;

public interface AccountDao extends JpaRepository<Account, Long>{
	@Modifying
	@Query("update Account a set a.accountBalance = :newamount where a.accountNumber = :accountnumber")
	void updateAccountBalance(@Param("accountnumber") long accountNumber,@Param("newamount") double amount);
	
	@Query(value = "select a from Account a where a.customer.personId=:customerId")
	public List<Account> getAccountByCustomerId(@Param("customerId") int customerId);
}
