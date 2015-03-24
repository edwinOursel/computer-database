package com.excilys.cdb.mapper.dtoMapper;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dto.CompanyDto;

public class CompanyDtoMapper {

	//Un peu inutile ...
	public CompanyDto map(Company c) {
		CompanyDto dto = new CompanyDto();
		dto.setId(c.getId());
		dto.setName(c.getName());
		return dto;
	}
	
	public Company unMap(CompanyDto dto) {
		Company c = new Company();
		c.setId(dto.getId());
		c.setName(dto.getName());
		return c;
	}

}
