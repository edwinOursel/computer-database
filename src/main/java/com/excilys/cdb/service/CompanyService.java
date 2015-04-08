package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dao.CompanyDAO;

@Component
public class CompanyService {

	@Autowired
	private CompanyDAO companyDAO;
	
	public List<Company> getAll() throws ServiceException {
		try {			
			return companyDAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Long> getAllCompaniesId() {
        return companyDAO.getAllCompaniesId();
    }
	
	 public Company getById(Long id) {
	        if (id <= 0) {
	            throw new IllegalArgumentException();
	        }
	        return companyDAO.getById(id);
	    }
}
