package com.rays.service;

import com.rays.common.BaseServiceInt;
import com.rays.dto.UserDto;

public interface UserServiceInt extends BaseServiceInt<UserDto> {
	
	public UserDto authenticate(String loginId, String password);

}
