package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dao.CompanyRepository;

@Component
public class CompanyService {
	
	@Autowired
	private CompanyRepository repository;
		
	
	public List<Company> getAll() throws ServiceException {
		return repository.findAll();
	}
	
	public List<Long> getAllCompaniesId() {
        return repository.getAllIds();
    }
	
	 public Company getById(Long id) {
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        return repository.findById(id);
    }
}
