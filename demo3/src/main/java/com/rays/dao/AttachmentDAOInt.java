package com.rays.dao;

import com.rays.dto.AttachmentDto;

public interface AttachmentDAOInt {
	
	public long add(AttachmentDto dto);
	
	public void update(AttachmentDto dto);
	
	public void delete(AttachmentDto  dto);
	
	public AttachmentDto findByPk(long pk);

}
