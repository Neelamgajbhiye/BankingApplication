package com.xoriant.banking.api.DTO;
/*
 * save the old and new and pass it to the controller which we get from UI/form of change password
 */
public class PasswordChangeCommand {

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
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
	@Override
	public String toString() {
		return "PasswordChangeCommand [oldPassword=" + oldPassword + ", newPassword=" + newPassword
				+ ", confirmPassword=" + confirmPassword + "]";
	}
	
}
