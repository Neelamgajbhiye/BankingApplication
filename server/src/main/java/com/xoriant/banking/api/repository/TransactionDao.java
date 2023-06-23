package com.xoriant.banking.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xoriant.banking.api.model.Transactions;

public interface TransactionDao extends JpaRepository<Transactions, Integer >{
	@Query(value = "select t from Transactions t where t.fromAccount.accountNumber=:accountNumber")
	public List<Transactions> findAllTransactionByAccountNumber(@Param("accountNumber") long accountNumber);

}
