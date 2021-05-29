package com.esertopcu.web.dto;

import com.esertopcu.validation.PasswordMatches;
import com.esertopcu.validation.ValidPassword;

//custom validation obje bazinda
@PasswordMatches
public class PasswordDto {

	private String oldPassword;
	
	// custom validation
	@ValidPassword
	private String newPassword;
	private String matchingPassword;
	
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
	
	public String getMatchingPassword() {
		return matchingPassword;
	}
	
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

}
