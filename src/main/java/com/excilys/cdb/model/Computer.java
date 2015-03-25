package com.excilys.cdb.model;

import java.time.LocalDateTime;

public class Computer {
	private long id;
	private String name;
	private LocalDateTime introducedDate;
	private LocalDateTime discontinuedDate;
	private Company company;
	
	public Computer() {
    }

    public Computer(Long id, String name, LocalDateTime introduced,
                    LocalDateTime discontinued, Company company) {
        this.id = id;
        this.name = name;
        this.introducedDate = introduced;
        this.discontinuedDate = discontinued;
        this.company = company;
    }

    public Computer(String name, LocalDateTime introduced,
                    LocalDateTime discontinued, Company company) {
        this(null, name, introduced, discontinued, company);
    }
	
	
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public LocalDateTime getIntroducedDate() {
		return introducedDate;
	}
	
	public LocalDateTime getDiscontinuedDate() {
		return discontinuedDate;
	}
		
	
	public void setId(long id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIntroducedDate(LocalDateTime introduced) {
		this.introducedDate = introduced;
	}
	
	public void setDiscontinuedDate(LocalDateTime discontinued) {
		this.discontinuedDate = discontinued;
	}
	
	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result
				+ ((discontinuedDate == null) ? 0 : discontinuedDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result
				+ ((introducedDate == null) ? 0 : introducedDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (discontinuedDate == null) {
			if (other.discontinuedDate != null)
				return false;
		} else if (!discontinuedDate.equals(other.discontinuedDate))
			return false;
		if (id != other.id)
			return false;
		if (introducedDate == null) {
			if (other.introducedDate != null)
				return false;
		} else if (!introducedDate.equals(other.introducedDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Computer [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", introduced=");
		builder.append(introducedDate);
		builder.append(", discontinued=");
		builder.append(discontinuedDate);
		builder.append(", company=");
		builder.append(company);
		builder.append("]");
		return builder.toString();
	}

}
