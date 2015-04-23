package com.excilys.cdb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.dao.ComputerRepository;

@Component
@Transactional(rollbackFor=DAOException.class)
public class ComputerService extends Service {
		
	@Autowired
	private ComputerRepository repository;
			   
    
	public long count() {
        return repository.count();
    }
	
	public List<Computer> getAll() throws ServiceException {
		return repository.findAll();
	}
	
	public Page<Computer> getAll(Pageable page) throws ServiceException {
		if (page == null) {
			throw new IllegalArgumentException();
		}
		return repository.findAll(page);
	}

	public Computer getById(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		return repository.findById(id);
	}

	public long create(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		return repository.save(computer).getId();
	}

	public void update(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		repository.save(computer);
	}

	public void delete(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		repository.delete(id);
	}
}
