package com.xoriant.banking.api.command;

import java.util.Calendar;

import com.xoriant.banking.api.model.Gender;
import com.xoriant.banking.api.model.Status;

/**
 * AddCustomerCommand is command to add customer
 * @author Purohit_h
 *
 */
public class AddCustomerCommand {
	
	private String personName;
	private Gender gender;
	
	private Status customerStatus;
	private String date;
	private int flatNo;
	private String city;
	private String state;
	private int pincode;
	private String telephoneNumber;
	private String email;
	private String username;
	private String password;
	
	

	
	public int getFlatNo() {
		return flatNo;
	}
	public void setFlatNo(int flatNo) {
		this.flatNo = flatNo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getPincode() {
		return pincode;
	}
	public void setPincode(int pincode) {
		this.pincode = pincode;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public Status getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(Status customerStatus) {
		this.customerStatus = customerStatus;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

	
	

}