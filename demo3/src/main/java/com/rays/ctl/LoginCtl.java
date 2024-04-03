package com.rays.ctl;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.UserDto;
import com.rays.form.LoginForm;
import com.rays.form.UserForm;
import com.rays.service.UserServiceInt;

@RestController
@RequestMapping(value = "Auth")
public class LoginCtl extends BaseCtl<UserForm, UserDto, UserServiceInt> {
	
	@PostMapping("login")
	public ORSResponse login (@RequestBody @Valid LoginForm form, BindingResult bindingResult) {
		
		ORSResponse res = validate(bindingResult);
		
		if (!res.isSucess()) {
			return res;
					
		}
		
		UserDto dto = baseservice.authenticate(form.getLoginId(), form.getPassword());
		
		if (dto !=null) {
			res.addData(dto);	
		}else {
			res.addMessage("LoginId & password is invalid");
		}
		
		
		return res;
		
		
	}
	
	

}
