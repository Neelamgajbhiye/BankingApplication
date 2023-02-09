package com.xoriant.banking.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xoriant.banking.api.model.Branch;

public interface BranchDao extends JpaRepository<Branch, Integer> {

}
