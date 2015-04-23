package com.excilys.cdb.persistence.dto;

import java.io.Serializable;

public class CompanyDto implements Serializable {
	

	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", name=" + name + "]";
	}
	
}
