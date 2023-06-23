package com.xoriant.banking.api.command;

/**
 * DeleteCustomerCommand is command to use delete customer
 * @author Purohit_h
 *
 */
public class DeleteCustomerCommand {
	private int personId;

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

}
