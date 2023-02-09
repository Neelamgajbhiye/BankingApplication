package com.xoriant.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.banking.api.model.Address;

public interface AddressDao extends JpaRepository<Address, Integer>{

}
