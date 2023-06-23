package com.xoriant.banking.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.xoriant.banking.api.DTO.EditCustomerCommand;
import com.xoriant.banking.api.model.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	Customer findByPersonId(Integer personId);

	void deleteByPersonId(Integer personId);
	
	@Query(value = "select c from Customer c where c.manager.personId=:managerId")
	public List<Customer> getCustomerByMangerId(@Param("managerId") int managerId);
	
//	@Modifying
//	@Query(value="update Customer c set c.personName = :#{#cmd.name} "
//			+ "and c.email:#{#cmd.email} and c.user.userName=:#{#cmd.userName} "
//			+ "and c.address.flatNo:#{#cmd.flatNumber} and c.address.city=:#{#cmd.city} "
//			+ "and c.address.state:#{#cmd.state} and c.address.pincode=:#{#cmd.pincode} "
//			+ "and c.telephoneNumber:#{#cmd.phone} and c.gender=:#{#cmd.gender} "
//			+ "and c.dob:#{#cmd.dateOfBirth} "
//			+ " where c.personId = :personId")
//public void updateCustomer(@Param("personId") int personId,@Param("cmd") EditCustomerCommand cmd);
}
