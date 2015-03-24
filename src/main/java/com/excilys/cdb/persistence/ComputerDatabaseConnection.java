package com.excilys.cdb.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.exception.PersistenceException;

public enum ComputerDatabaseConnection {
	INSTANCE;

	private Properties properties;
	private String url;

	private ComputerDatabaseConnection() {
		try {
			loadConfigFile();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	public Connection getInstance() throws PersistenceException {
		Connection connection = null;

		try {			
			connection = DriverManager.getConnection(url, properties);
		} catch (SQLException e) {
			throw new PersistenceException(e.getMessage());
		}

		return connection;
	}

	private void loadConfigFile() throws IOException, InstantiationException,
    IllegalAccessException, ClassNotFoundException {
		if (properties == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			properties = new Properties();
			
			InputStream is = ComputerDatabaseConnection.class
					.getClassLoader().getResourceAsStream("env-config.properties");
			if (is == null) {
				is = ComputerDatabaseConnection.class
				.getClassLoader().getResourceAsStream("config.properties");
			}
			try (final InputStream i = is) {
				properties.load(i);
				url = properties.getProperty("url");
			}	
		}
	}
}
