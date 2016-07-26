package com.hl.validate.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.hl.validate.annotation.Phone;

public class PhoneValidator implements ConstraintValidator<Phone, String>{

	@Override
	public void initialize(Phone arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isValid(String mobiles, ConstraintValidatorContext arg1) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		  
		Matcher m = p.matcher(mobiles);  
		  
		System.out.println(m.matches()+"---");  
		  
		return m.matches(); 
	}

}
