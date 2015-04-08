package com.excilys.cdb.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.excilys.cdb.exception.DAOException;

public abstract class Service {
	
	
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
}
