package com.rays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.AttachmentDAOInt;
import com.rays.dto.AttachmentDto;

@Service
@Transactional
public class AttachmentServiceImpl implements AttachmentServiceInt {

	@Autowired
	public AttachmentDAOInt dao;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(AttachmentDto dto) {
		long pk = dao.add(dto);

		return pk;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void upadte(AttachmentDto dto) {
		dao.update(dto);

	}

	@Transactional(readOnly = true)
	public AttachmentDto findById(long pk) {

		AttachmentDto dto = dao.findByPk(pk);

		return dto;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {

		try {
			AttachmentDto dto = findById(id);
			dao.delete(dto);

		} catch (RuntimeException e) {
			System.out.println(e.getMessage());

		}

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public long save(AttachmentDto dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			upadte(dto);
		}else {
			id = add(dto);
			
		}
		
		return id;
	}

}
