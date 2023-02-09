package com.xoriant.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.banking.api.model.Manager;

public interface ManagerDao extends JpaRepository<Manager, Integer>{

}
