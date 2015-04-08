package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComputerDatabaseConnectionFactory {


	@Autowired
	private DataSource dataSource;
	
	
	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}
