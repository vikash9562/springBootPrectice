package com.rays.common;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public class BaseDto {
	
	@Id
	@GeneratedValue(generator = "ncsPk")
	@GenericGenerator(name = "ncsPk", strategy = "increment")
	@Column(name = "ID", unique = true, nullable = false)
	
	protected Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	

}
