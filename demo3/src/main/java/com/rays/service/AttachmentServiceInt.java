package com.rays.service;

import com.rays.dto.AttachmentDto;

public interface AttachmentServiceInt {
	
	public long add (AttachmentDto dto);
	
	public void upadte(AttachmentDto dto);
	
	public void delete(long id);
	
	public AttachmentDto findById(long pk);
	
	public long save(AttachmentDto dto);
	

}
