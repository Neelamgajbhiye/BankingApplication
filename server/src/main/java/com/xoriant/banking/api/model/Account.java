package com.xoriant.banking.api.model;


import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * This is Account Model Class which is used as a data traveller between different layers
 * @author Neelam_g
 *
 */
@Entity
@JsonIgnoreProperties({"transaction"})
public class Account {
	/**
	 * accountNumber field is use to uniquely indentify account
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long accountNumber;

	/**
	 * customer field is used as foreign key over here
	 */
	@ManyToOne
	@JoinColumn(name="personId")
	private Customer customer;
	/**
	 * accountBalance field is use to store balance of that account
	 */
	private double accountBalance;
	/**
	 * minimumBalance field is use to store minimum balance of that account
	 */
	private double minimumBalance;
	/**
	 * accountStatus field is use to store status of that account
	 */
	@Enumerated(EnumType.STRING)
	private Status accountStatus;
	/**
	 * date field is use to store date when the account is opened
	 */
	@Temporal(TemporalType.DATE)
	private Calendar date;
	/**
	 * date field is use to store date when the account should be closed
	 */
	@Temporal(TemporalType.DATE)
	private Calendar dateOfClosure;
	/**
	 * description field is use to store description of that account
	 */
	private String description;
	/**
	 * description field is use to store description of that account
	 */
	@Enumerated(EnumType.STRING)
	private AccountType accountType;

	/**
	 * transaction field is use to store list of transactions
	 */
	@OneToMany(cascade = CascadeType.REMOVE,mappedBy = "fromAccount")
	private List<Transactions> transaction;

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public double getMinimumBalance() {
		return minimumBalance;
	}

	public void setMinimumBalance(double minimumBalance) {
		this.minimumBalance = minimumBalance;
	}

	public Status getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(Status accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Calendar getDateOfClosure() {
		return dateOfClosure;
	}

	public void setDateOfClosure(Calendar dateOfClosure) {
		this.dateOfClosure = dateOfClosure;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public List<Transactions> getTransaction() {
		return transaction;
	}

	public void setTransaction(List<Transactions> transaction) {
		this.transaction = transaction;
	}
	
	
	
}
