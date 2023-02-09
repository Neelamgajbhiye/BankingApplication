package com.xoriant.banking.api.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xoriant.banking.api.model.PersonalInfo;
import com.xoriant.banking.api.repository.PersonalInfoDao;
@Transactional
@Service
public class PersonalInfoServieImpl implements PersonalInfoService{

	
	@Autowired
	PersonalInfoDao personalInfoDao;
	@Override
	public PersonalInfo getByUserId(int userId) {
		// TODO Auto-generated method stub
		return personalInfoDao.findByUserId(userId);
	}

}
