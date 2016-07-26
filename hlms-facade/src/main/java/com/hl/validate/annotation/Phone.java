package com.hl.validate.annotation;

import java.lang.annotation.ElementType;  
import java.lang.annotation.Retention;  
import java.lang.annotation.RetentionPolicy;  
import java.lang.annotation.Target;  

import javax.validation.Constraint;
import javax.validation.Payload;

import com.hl.validate.validator.PhoneValidator;

@Target({ElementType.FIELD, ElementType.METHOD})  
@Retention(RetentionPolicy.RUNTIME)  
@Constraint(validatedBy=PhoneValidator.class)
public @interface Phone {

	String message() default"手机号格式错误";  
    
    Class<?>[] groups() default {};  
     
    Class<? extends Payload>[] payload() default {}; 
}
