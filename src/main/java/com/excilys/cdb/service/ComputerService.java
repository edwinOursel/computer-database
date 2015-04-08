package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDatabaseConnectionFactory;
import com.excilys.cdb.persistence.dao.ComputerDAO;

@Component
@Transactional(rollbackFor=DAOException.class)
public class ComputerService extends Service {
	
	@Autowired
	private ComputerDatabaseConnectionFactory cdcf;
	
	@Autowired
	private ComputerDAO computerDAO;
	
	private final ThreadLocal<Connection> connection = new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue()
        {
            try {
				return cdcf.getConnection();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }
    };	
    
    
	public int count() {
        return computerDAO.count();
    }
	
	public List<Computer> getAll() throws ServiceException {
		try {
			return computerDAO.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Computer> getAll(Page page) throws ServiceException {
		if (page == null) {
			throw new IllegalArgumentException();
		}
		try {
			return computerDAO.getAll(page);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public Computer getById(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		try {
			return computerDAO.getById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public long create(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		try {
			return computerDAO.create(computer);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void update(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		try {
			computerDAO.update(computer);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void delete(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		try {
			computerDAO.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
