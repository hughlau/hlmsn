package com.hl.validate.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.hl.validate.annotation.Phone;

public class User_Register {

	private String username;
	
	private String password;
	
	private String email;
	
	private String phone;

	@NotBlank(message="用户名不能为空")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@NotBlank(message="密码不能为空")
	@NotNull(message="密码不能为空") 
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@NotBlank(message="Email不能为空")
	@Email(message="Email格式不正确")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@NotBlank(message="手机号不能为空")
	@Phone(message="手机号格式不正确")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
