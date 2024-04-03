package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.UserDto;

@Repository
public class UserDAOImpl extends BaseDAOImpl<UserDto> implements UserDAOInt {

	@Override
	public Class<UserDto> getDTOClass() {
		return UserDto.class;
	}

	@Override
	public List<Predicate> getWhereClause(CriteriaBuilder builder, Root qRoot, UserDto dto) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {

			whereCondition.add(builder.like(qRoot.get("firstName"), dto.getFirstName()));

		}
		return whereCondition;
	}

}
