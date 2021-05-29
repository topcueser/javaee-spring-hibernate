package com.esertopcu.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({
	ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME) // ne zaman calisacagini belirtiyoruz
@Constraint(validatedBy = EmailValidator.class)
@Documented
public @interface ValidEmail {

	String message() default "Invalid email";
	
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
