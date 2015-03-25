package com.excilys.cdb.persistence.dto;

import java.io.Serializable;

public class ComputerDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String name;
	private String introducedDate;
	private String discontinuedDate;
	private long company;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroducedDate() {
		return introducedDate;
	}
	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}
	public String getDiscontinuedDate() {
		return discontinuedDate;
	}
	public void setDiscontinuedDate(String discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}
	public long getCompany() {
		return company;
	}
	public void setCompany(long company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "ComputerDto [id=" + id + ", name=" + name + ", introducedDate="
				+ introducedDate + ", discontinuedDate=" + discontinuedDate
				+ ", company=" + company + "]";
	}
	
}
