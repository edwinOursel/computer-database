package com.excilys.cdb.persistence.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.exception.TransactionException;
import com.excilys.cdb.persistence.ComputerDatabaseConnectionFactory;

public class SqlExecuteQueryTransaction implements Transaction<StatementPreparator, ResultSet> {

	static private final ComputerDatabaseConnectionFactory cf = ComputerDatabaseConnectionFactory.INSTANCE;

	private PreparedStatement statement;
	private Connection c = null;
	
	@Override
	public ResultSet performOperation(StatementPreparator p) {
		try {			
			statement = c.prepareStatement(p.getStatement());
			p.prepare(statement);
			ResultSet r = statement.executeQuery();
			statement.closeOnCompletion();			
			return r;
		} catch (SQLException e) {
			throw new TransactionException(e);
		}
	}

	@Override
	public List<ResultSet> performOperations(List<StatementPreparator> l) {				
		try {
			c = cf.getConnection();
			c.setAutoCommit(false);			
			List<ResultSet> r = l.stream().map(this::performOperation).collect(Collectors.toList());
			c.commit();
			return r;
		} catch (SQLException e) {
			if (c != null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			} else {
				e.printStackTrace();
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;		
	}
}
