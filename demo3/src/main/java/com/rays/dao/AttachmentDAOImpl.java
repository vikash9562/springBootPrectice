package com.rays.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.rays.dto.AttachmentDto;

@Repository
public class AttachmentDAOImpl implements AttachmentDAOInt{
	
	@PersistenceContext
	public EntityManager entityManager;

	@Override
	public long add(AttachmentDto dto) {
		entityManager.persist(dto);
		return dto.getId();
	}

	@Override
	public void update(AttachmentDto dto) {
		entityManager.merge(dto);
		
	}

	@Override
	public void delete(AttachmentDto dto) {
		entityManager.remove(dto);
		
	}

	@Override
	public AttachmentDto findByPk(long pk) {
		AttachmentDto dto = entityManager.find(AttachmentDto.class, pk);
		return dto;
	}

}
