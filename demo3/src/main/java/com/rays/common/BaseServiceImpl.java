package com.rays.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class BaseServiceImpl <T extends BaseDto, D extends BaseDAOInt<T>> implements BaseServiceInt<T>{
	
	@Autowired
	public D basedao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(T dto) {
		long pk = basedao.add(dto);
		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(T dto) {
		basedao.update(dto);
		
	}


	@Transactional(readOnly = true)
	public T findById(long pk) {
		T dto = basedao.findByPk(pk);
		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		try {
			T dto = findById(id);
			basedao.delete(dto);
			
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
		}
		
	}

	@Transactional(readOnly = true)
	public List search(T dto, int pageNo, int pageSize) {
		
		List list = basedao.search(dto, pageNo, pageSize);
		
		return list;
	}



	
}
