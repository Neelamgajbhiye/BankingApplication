package com.xoriant.banking.api.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.Setter;


/**
 * This is PersonalInfo Model Class which is used as a data traveller between different layers
 * @author Neelam_g
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonalInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int personId;
	protected String personName;
	protected String telephoneNumber;
	@Temporal(TemporalType.DATE)
	protected Calendar dob;
	protected String email;
	@Enumerated(EnumType.STRING)
	protected Gender gender;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	protected User user;
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="branchId")
	protected Branch branch;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="addressId")
	protected Address address;
	public int getPersonId() {
		return personId;
	}
	public void setPersonId(int personId) {
		this.personId = personId;
	}
	public String getPersonName() {
		return personName;
	}
	public void setPersonName(String personName) {
		this.personName = personName;
	}
	public String getTelephoneNumber() {
		return telephoneNumber;
	}
	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}
	public Calendar getDob() {
		return dob;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Branch getBranch() {
		return branch;
	}
	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "PersonalInfo [personId=" + personId + ", personName=" + personName + ", telephoneNumber="
				+ telephoneNumber + ", dob=" + dob + ", email=" + email + ", gender=" + gender + ", user=" + user
				+ ", branch=" + branch + ", address=" + address + "]";
	}



	
}
