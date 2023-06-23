package com.xoriant.banking.api.DTO;

import com.xoriant.banking.api.model.PersonalInfo;


public class JwtResponse {
	
	private PersonalInfo personalInfo;
	private String jwtToken;
	public JwtResponse(PersonalInfo personalInfo, String jwtToken) {
		super();
		this.personalInfo = personalInfo;
		this.jwtToken = jwtToken;
	}
	public PersonalInfo getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfo personalInfo) {
		this.personalInfo = personalInfo;
	}
	public String getJwtToken() {
		return jwtToken;
	}
	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	
	
	
	
	

}
