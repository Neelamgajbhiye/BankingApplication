 	package com.xoriant.banking.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * This is Manager Model Class which is used as a data traveller between different layers
 * @author Neelam_g
 *
 */
@Entity
@JsonIgnoreProperties("customer")
public class Manager extends PersonalInfo {
	@OneToMany(mappedBy = "manager",cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
	private List<Customer> customer;

	public List<Customer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<Customer> customer) {
		this.customer = customer;
	}

	
	
}
