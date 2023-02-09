package com.xoriant.banking.api.command;

import com.xoriant.banking.api.model.AccountType;
import com.xoriant.banking.api.model.Gender;
import com.xoriant.banking.api.model.Status;



/**
 * EditCustomerCommand is command to use edit customer
 * @author Purohit_h
 *
 */
public class CustomerDTO {
	private int branchId;
	private int addressId;
	
	private int flatNo;
	private String city;
	private String state;
	private int pincode;
	
	private String branchName;
	private String IFSC;
	
	private Status customerStatus; // customer
	
	private int personId;
	private String personName;
	private Gender gender;// person
	
	private AccountType accountType; //account
	private long accountNumber;
	
	
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}



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

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getIFSC() {
		return IFSC;
	}

	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}

	public Status getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(Status customerStatus) {
		this.customerStatus = customerStatus;
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

}
