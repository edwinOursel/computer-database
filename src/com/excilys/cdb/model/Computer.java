package com.excilys.cdb.model;

import java.time.LocalDateTime;

public class Computer {
	private long id;
	private String name;
	private LocalDateTime introduced;
	private LocalDateTime discontinued;
	private long companyId;
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDateTime getIntroduced() {
		return introduced;
	}
	
	public LocalDateTime getDiscontinued() {
		return discontinued;
	}
	
	
	public long getCompanyId() {
		return companyId;
	}

	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIntroduced(LocalDateTime introduced) {
		this.introduced = introduced;
	}
	
	public void setDiscontinued(LocalDateTime discontinued) {
		this.discontinued = discontinued;
	}
	
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Computer [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", introduced=");
		builder.append(introduced);
		builder.append(", discontinued=");
		builder.append(discontinued);
		builder.append(", companyId=");
		builder.append(companyId);
		builder.append("]");
		return builder.toString();
	}

}
