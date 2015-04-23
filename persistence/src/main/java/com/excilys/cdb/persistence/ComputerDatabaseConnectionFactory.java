package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.DAOException;

@Component
public class ComputerDatabaseConnectionFactory {

	private Logger logger = LoggerFactory.getLogger(ComputerDatabaseConnectionFactory.class);
	
	@Autowired
	private BasicDataSource dataSource;	
	
	private final ThreadLocal<Connection> connection = new ThreadLocal<Connection>(){
        @Override
        protected Connection initialValue()
        {
            try {
            	Connection connection = dataSource.getConnection();
            	logger.debug("création de la connection " + connection.toString());
				return connection;
			} catch (SQLException e) {
				throw new DAOException(e);
			}
        }
    };
	

	public Connection getConnection() throws SQLException {
		return connection.get();
	}
	
	public void startTransaction() {
    	try {
    		connection.get().setAutoCommit(false);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
    }
    
	public void finishTransaction() {
    	try {
    		connection.get().setAutoCommit(true);
		} catch (SQLException e) {
			throw new DAOException(e);
		}
    }
	
}
