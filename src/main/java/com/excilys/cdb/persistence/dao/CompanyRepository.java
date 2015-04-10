package com.excilys.cdb.persistence.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.excilys.cdb.model.Company;

public interface CompanyRepository extends CrudRepository<Company, Long> {

	Company getById(Long id);
	List<Company> getAll();
	List<Long> getAllCompaniesId();
	
}
