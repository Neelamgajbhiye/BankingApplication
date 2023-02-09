package com.xoriant.banking.api.service;

import com.xoriant.banking.api.DTO.AddCustomerDTO;
import com.xoriant.banking.api.model.PersonalInfo;

public interface PersonalInfoService {

	PersonalInfo getByUserId(int userId);
	
	
}
