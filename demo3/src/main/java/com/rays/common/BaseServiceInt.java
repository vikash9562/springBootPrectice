package com.rays.common;

import java.util.List;

public interface BaseServiceInt<T extends BaseDto> {

	public long add(T dto);

	public void update(T dto);

	public void delete(long id);

	public T findById(long pk);

	public List search(T dto, int pageNo, int pageSize);

}
