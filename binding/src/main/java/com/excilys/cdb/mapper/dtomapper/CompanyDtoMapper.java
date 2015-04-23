package com.excilys.cdb.mapper.dtomapper;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dto.CompanyDto;

@Component
@Scope("prototype")
public class CompanyDtoMapper implements DtoMapper<CompanyDto, Company> {

	@Override
	public CompanyDto map(Company c) {
		CompanyDto dto = new CompanyDto();
		dto.setId(c.getId());
		dto.setName(c.getName());
		return dto;
	}
	
	@Override
	public Company unMap(CompanyDto dto) {
		Company c = new Company();
		c.setId(dto.getId());
		c.setName(dto.getName());
		return c;
	}

}
