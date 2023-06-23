package com.xoriant.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.xoriant.banking.api.model.PersonalInfo;



/**
 * This is PersonalInfoDao interface
 * To abstract the retrieval of data from a data resource such as a database.
 * @author Neelam 
 *
 */
public interface PersonalInfoDao extends JpaRepository<PersonalInfo, Integer> {

	@Query("from PersonalInfo p where p.user.userId=:userId")
	PersonalInfo findByUserId(@Param(value = "userId") int userId);

	
	
		
}
