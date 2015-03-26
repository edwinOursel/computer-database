package com.excilys.cdb.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.excilys.cdb.exception.PersistenceException;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public enum ComputerDatabaseConnectionFactory {
	INSTANCE;

	private Properties properties;
	private String url;
	private BoneCP connectionPool;
	
	
	private ComputerDatabaseConnectionFactory() {
		try {
			loadConfigFile();
			poolInit();
		} catch (Exception e) {
			throw new PersistenceException(e);
		}
	}

	private void poolInit() throws Exception {
		BoneCPConfig config = new BoneCPConfig(properties);
		
		config.setJdbcUrl(url);			
		config.setMinConnectionsPerPartition(5);
		config.setMaxConnectionsPerPartition(10);
		config.setPartitionCount(10);		
		
		connectionPool = new BoneCP(config);
	}
	
	public Connection getConnection() throws SQLException {
		return connectionPool.getConnection();
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
			
			InputStream is = ComputerDatabaseConnectionFactory.class
					.getClassLoader().getResourceAsStream("env-config.properties");
			if (is == null) {
				is = ComputerDatabaseConnectionFactory.class
				.getClassLoader().getResourceAsStream("config.properties");
			}
			try (final InputStream i = is) {
				properties.load(i);
				url = properties.getProperty("url");
			}	
		}
	}
}
