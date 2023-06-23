package com.xoriant.banking.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * This is Customer Model Class which is used as a data traveller between different layers
 * @author Neelam_g
 *
 */
@Entity
@JsonIgnoreProperties({"account"})

public class Customer extends PersonalInfo {
	
	@ManyToOne(cascade = CascadeType.MERGE,fetch = FetchType.EAGER)
	@JoinColumn(name = "managerId")
	private Manager manager;
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "customer")
	private List<Account> account;
	@Enumerated(EnumType.STRING)
	private Status customerStatus;
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public List<Account> getAccount() {
		return account;
	}
	public void setAccount(List<Account> account) {
		this.account = account;
	}
	public Status getCustomerStatus() {
		return customerStatus;
	}
	public void setCustomerStatus(Status customerStatus) {
		this.customerStatus = customerStatus;
	}
//	@Override
//	public String toString() {
//		return "Customer [manager=" + manager + ", account=" + account + ", customerStatus=" + customerStatus + "]";
//	}


	
}

	
