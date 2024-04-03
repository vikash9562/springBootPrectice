package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.common.BaseServiceInt;
import com.rays.dao.UserDAOInt;
import com.rays.dto.UserDto;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<UserDto, UserDAOInt> implements UserServiceInt{
	

	@Override
	public UserDto authenticate(String loginId, String password) {

		 UserDto dto = basedao.findByUniqeKey("loginId", loginId);
		
		if (dto !=null) {
			if (dto.getPassword().equals(password)) {
				return dto;
				
			}
			
		}
		return null;
	}

}
