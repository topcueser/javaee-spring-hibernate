package com.esertopcu.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.esertopcu.validation.PasswordMatches;
import com.esertopcu.validation.ValidEmail;
import com.esertopcu.validation.ValidPassword;

// custom annotation
@PasswordMatches
public class UserDto {

	@NotNull
	@Size(min = 3, max = 15, message = "FirstName en az 3, en fazla 15 karakter olabilir")
	private String firtname;
	private String lastname;
	private String username;
	
	// custom validation
	@ValidEmail
	private String email;
	
	// custom validation
	@ValidPassword
	private String password;
	private String matchingPassword;
	
	public String getFirtname() {
		return firtname;
	}
	
	public void setFirtname(String firtname) {
		this.firtname = firtname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getMatchingPassword() {
		return matchingPassword;
	}
	
	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
}
