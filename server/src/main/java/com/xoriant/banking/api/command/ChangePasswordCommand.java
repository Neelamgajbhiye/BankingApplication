package com.xoriant.banking.api.command;

/**
 * ChangePasswordCommand is command to change Passsword
 * @author Purohit_h
 *
 */
public class ChangePasswordCommand {
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private int userId;
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
}
