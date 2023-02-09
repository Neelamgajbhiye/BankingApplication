package com.xoriant.banking.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
/**
 * This is Address Model Class which is used as a data traveller between different layers
 * @author Neelam_g
 *
 */
@Entity
public class Address {
	/**
	 * addressId field is use to uniquely indentify addresses
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int addressId;
	/**
	 * flatNo field is use to store flat number of person's address
	 */
	private int flatNo;
	/**
	 * city field is use to store city 
	 */
	private String city;
	/**
	 * state field is use to store state
	 */
	private String state;
	/**
	 * pincode field is use to store pincode
	 */
	private int pincode;
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
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
	
	
	
}
