package com.xoriant.banking.api.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/** 
 * This is Account Model Class which is used as a data traveller between different layers
 * @author Purohit_h
 *
 */
@Entity
@Data
public class Branch {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int branchId;
	private String branchName;
	private String IFSC;
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getIFSC() {
		return IFSC;
	}
	public void setIFSC(String iFSC) {
		IFSC = iFSC;
	}
	
	

}
