package com.excilys.cdb.mapper.dtoMapper;

import java.time.LocalDateTime;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.dao.CompanyDAO;
import com.excilys.cdb.persistence.dto.ComputerDto;

public class ComputerDtoMapper {

	public ComputerDto map(Computer c) {
		ComputerDto dto = new ComputerDto();
		dto.setName(c.getName());
		dto.setId(c.getId());
		dto.setCompany(c.getCompany().getId());
		dto.setIntroducedDate(c.getIntroducedDate().toString());
		dto.setDiscontinuedDate(c.getDiscontinuedDate().toString());
		return dto;
	}
	
	public Computer unMap(ComputerDto dto) {
		Computer c = new Computer();
		c.setId(dto.getId());
		c.setName(dto.getName());		
		c.setCompany(CompanyDAO.INSTANCE.getById(dto.getCompany()));
		c.setIntroducedDate(LocalDateTime.parse(dto.getIntroducedDate()));
		c.setDiscontinuedDate(LocalDateTime.parse(dto.getDiscontinuedDate()));
		return c;
	}

}
