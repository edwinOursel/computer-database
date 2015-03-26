package com.excilys.cdb.persistence.transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.exception.TransactionException;
import com.excilys.cdb.persistence.ComputerDatabaseConnectionFactory;

public class SqlExecuteTransaction implements Transaction<StatementPreparator, Boolean> {

	static private final ComputerDatabaseConnectionFactory cf = ComputerDatabaseConnectionFactory.INSTANCE;

	private PreparedStatement statement;
	private Connection c = null;
	
	@Override
	public Boolean performOperation(StatementPreparator p) {
		try {			
			statement = c.prepareStatement(p.getStatement());
			p.prepare(statement);
			Boolean r = statement.execute();
			statement.close();
			return r;
		} catch (SQLException e) {
			throw new TransactionException(e);
		}
	}

	@Override
	public List<Boolean> performOperations(List<StatementPreparator> l) {				
		try {
			c = cf.getConnection();
			c.setAutoCommit(false);			
			List<Boolean> r = l.stream().map(this::performOperation).collect(Collectors.toList());
			c.commit();
			return r;
		} catch (SQLException e) {
			if (c != null) {
				try {
					c.rollback();
				} catch (SQLException e1) {
					throw new TransactionException(e1);
				}
			} else {
				throw new TransactionException(e);
			}
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				throw new TransactionException(e);
			}
		}
		return null;		
	}
}
