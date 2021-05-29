package com.esertopcu.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.esertopcu.web.dto.PasswordDto;
import com.esertopcu.web.dto.UserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

	@Override
	public void initialize(PasswordMatches obj) {
		
	}
	
	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		
		if(obj.getClass().getSimpleName().equals("UserDto")) {
			UserDto userDto = (UserDto)obj;
			return userDto.getPassword().equals(userDto.getMatchingPassword());
		}else {
			PasswordDto passwordDto = (PasswordDto)obj;
			return passwordDto.getNewPassword().equals(passwordDto.getMatchingPassword());	
		}
	}

}
