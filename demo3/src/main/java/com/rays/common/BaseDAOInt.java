package com.rays.common;

import java.util.List;

public interface BaseDAOInt <T extends BaseDto>{
	
	public long add(T dto);
	
	public void update(T dto);
	
	public void delete(T dto);
	
	public T findByPk(Long pk);
	
	public T findByUniqeKey(String attribute, String value);
	
	public List  search (T dto, int pageNo, int pageSize);

}
