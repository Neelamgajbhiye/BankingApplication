package com.xoriant.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.banking.api.model.Role;

public interface RoleDao extends JpaRepository<Role, Integer> {

}
