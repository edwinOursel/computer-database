package com.excilys.cdb.mapper.dtomapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.dao.CompanyRepository;
import com.excilys.cdb.persistence.dto.ComputerDto;

@Component
@Scope("prototype")
public class ComputerDtoMapper implements DtoMapper<ComputerDto, Computer>  {
	
	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public ComputerDto map(Computer c) {
		ComputerDto dto = new ComputerDto();
		dto.setName(c.getName());
		dto.setId(c.getId());
		dto.setCompany((c.getCompany() == null)? null : c.getCompany().getName());
		dto.setCompanyId((c.getCompany() == null)? 0 : c.getCompany().getId());
		dto.setIntroduced((c.getIntroducedDate() == null)? null : c.getIntroducedDate().toLocalDate().toString());
		dto.setDiscontinued((c.getDiscontinuedDate() == null)? null : c.getDiscontinuedDate().toLocalDate().toString());
		return dto;
	}
	
	@Override
	public Computer unMap(ComputerDto dto) {
		Computer c = new Computer();
		c.setId(dto.getId());
		c.setName(dto.getName());		
		c.setCompany(companyRepository.findById(dto.getCompanyId()));
		c.setIntroducedDate(LocalDateTime.parse(dto.getIntroduced(), 
				DateTimeFormatter.ISO_LOCAL_DATE_TIME.withLocale(LocaleContextHolder.getLocale())));
		c.setDiscontinuedDate(LocalDateTime.parse(dto.getDiscontinued(), 
				DateTimeFormatter.ISO_LOCAL_DATE_TIME.withLocale(LocaleContextHolder.getLocale())));
		return c;
	}

}
