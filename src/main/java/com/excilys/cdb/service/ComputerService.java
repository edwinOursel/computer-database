package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDatabaseConnectionFactory;
import com.excilys.cdb.persistence.dao.ComputerRepository;

@Component
@Transactional(rollbackFor=DAOException.class)
public class ComputerService extends Service {
	
	@Autowired
	private static ComputerDatabaseConnectionFactory cdcf;
	
	@Autowired
	private ComputerRepository repository;
	
		
    
    
	public long count() {
        return repository.count();
    }
	
	public List<Computer> getAll() throws ServiceException {
		try {
			return repository.findAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Computer> getAll(Page page) throws ServiceException {
		if (page == null) {
			throw new IllegalArgumentException();
		}
		try {
			return repository.findAll(page);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public Computer getById(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		try {
			return repository.findById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public long create(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		try {
			return repository.save(computer).getId();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void update(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		try {
			repository.save(computer);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void delete(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		try {
			repository.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
