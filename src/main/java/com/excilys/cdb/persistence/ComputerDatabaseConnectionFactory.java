package com.excilys.cdb.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ComputerDatabaseConnectionFactory {


	@Autowired
	@Qualifier("dataSource")
	private BasicDataSource dataSource;
	
	
	public void setDataSource(BasicDataSource dataSource) {
		this.dataSource = dataSource;
	}


	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
	
}
