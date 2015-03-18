package com.excilys.cdb.service;

import java.util.List;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.dao.CompanyDAO;

public enum CompanyService {
	INSTANCE;

	public List<Company> getAll() throws ServiceException {
		try {
			return CompanyDAO.INSTANCE.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
