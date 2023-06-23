package com.xoriant.banking.api.DTO;

/*
 * save the customerId and pass it to the controller which we get from UI/form of Delete and Edit Customer
 */
public class EditCustomerDataEntry {
	private Integer customerId;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	

}
