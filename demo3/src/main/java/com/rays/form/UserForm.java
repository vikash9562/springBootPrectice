package com.rays.form;

import javax.validation.constraints.NotEmpty;

import com.rays.common.BaseDto;
import com.rays.common.BaseForm;
import com.rays.dto.UserDto;

public class UserForm extends BaseForm {

	@NotEmpty(message = "firstName is required")
	private String firstName;

	@NotEmpty(message = "lastName is required")
	private String lastName;

	@NotEmpty(message = "loginId is required")
	private String loginId;

	@NotEmpty(message = "password is required")
	private String password;

	public UserForm() {

	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
		@Override
		public BaseDto getDto() {
			UserDto dto = initDTO(new UserDto());
			
			dto.setFirstName(firstName);
			dto.setLastName(lastName);
			dto.setLoginId(loginId);
			dto.setPassword(password);
			return dto;
			
		}

}
