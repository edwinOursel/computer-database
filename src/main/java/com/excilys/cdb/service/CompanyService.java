package com.excilys.cdb.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dao.CompanyRepository;

@Component
public class CompanyService {

	Logger logger = LoggerFactory.getLogger(CompanyService.class);
	
	@Autowired
	private CompanyRepository repository;
		
	public List<Company> getAll() throws ServiceException {
		try {			
			logger.debug("CompanyService.getAll appelé");
			return repository.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getAllCompaniesId() {
		logger.debug("CompanyService.getAllCompaniesId appelé");
        return repository.getAllCompaniesId();
    }
	
	 public Company getById(Long id) {
		logger.debug("CompanyService.getAllCompaniesId appelé avec Id : " + id);
        if (id <= 0) {
            throw new IllegalArgumentException();
        }
        return repository.getById(id);
    }
}
