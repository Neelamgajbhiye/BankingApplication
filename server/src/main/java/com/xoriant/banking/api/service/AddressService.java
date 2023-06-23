package com.xoriant.banking.api.service;

import com.xoriant.banking.api.model.Address;

public interface AddressService {
	
	
	public Integer create(Address address);
	public Address getAddressByAddressId(Integer addressId);
	public void editAddress(Address address);
}
