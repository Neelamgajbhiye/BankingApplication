package com.xoriant.banking.api.DTO;


/*
 * Set the model in which the data of the edit customer form will be sent and than can be used by the 
 * controller
 */
public class EditCustomerCommand {
	private String name;
	private String userName;
//	private String password;
	private int flatNumber;
	private String street;
	private String city;
	private String state;
	private Integer pincode;
	private String phone;
	private String email;
	private String gender;
	private String dateOfBirth;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getPincode() {
		return pincode;
	}
	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public int getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(int flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
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
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
//	@Override
//	public String toString() {
////		return "RegisterCustomerCommand [name=" + name + ", loginName=" + userName + ", password=" + password
//				+ ", flatNumber=" + flatNumber + ", street=" + street + ", city=" + city + ", state=" + state + ", PIN="
//				+ pincode + ", phone=" + phone + ", email=" + email + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth
//				+ "]";
//	}
	
	

}
