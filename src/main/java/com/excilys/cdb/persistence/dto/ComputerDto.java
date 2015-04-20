package com.excilys.cdb.persistence.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.excilys.cdb.validation.LocaleDate;

public class ComputerDto implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	
	@NotNull
	@NotEmpty
	private String name;
	
	@LocaleDate
	private String introduced;
	
	@LocaleDate
	private String discontinued;
	private String company;
	
	@NotNull
	private long companyId;
	
	public long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}
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
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introducedDate) {
		this.introduced = introducedDate;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinuedDate) {
		this.discontinued = discontinuedDate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	@Override
	public String toString() {
		return "ComputerDto [id=" + id + ", name=" + name + ", introducedDate="
				+ introduced + ", discontinuedDate=" + discontinued
				+ ", company=" + company + "]";
	}
	
}
