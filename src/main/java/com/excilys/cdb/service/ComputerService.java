package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDatabaseConnectionFactory;
import com.excilys.cdb.persistence.dao.ComputerDAO;

public enum ComputerService {
	INSTANCE;
	
	
	private static final ThreadLocal<Connection> connection = new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue()
        {
            try {
				return ComputerDatabaseConnectionFactory.INSTANCE.getConnection();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }
    };
	
    private void startTransaction(Connection c) {
    	try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
    }
    
    private void finishTransaction(Connection c) {
    	try {
			c.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
    }
    
	public int count() {
        return ComputerDAO.INSTANCE.count();
    }
	
	public List<Computer> getAll() throws ServiceException {
		try {
			return ComputerDAO.INSTANCE.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
	
	public List<Computer> getAll(Page page) throws ServiceException {
		if (page == null) {
			throw new IllegalArgumentException();
		}
		try {
			return ComputerDAO.INSTANCE.getAll(page);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public Computer getById(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		try {
			return ComputerDAO.INSTANCE.getById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public long create(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		try {
			return ComputerDAO.INSTANCE.create(computer);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void update(Computer computer) throws ServiceException {
		if (computer == null) {
			throw new IllegalArgumentException();
		}
		try {
			ComputerDAO.INSTANCE.update(computer);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public void delete(long id) throws ServiceException {
		if (id <= 0) {
			throw new IllegalArgumentException();
		}
		try {
			ComputerDAO.INSTANCE.delete(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
