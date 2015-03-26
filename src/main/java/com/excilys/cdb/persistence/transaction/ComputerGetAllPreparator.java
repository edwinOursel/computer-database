package com.excilys.cdb.persistence.transaction;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.exception.PreparationException;

public class ComputerGetAllPreparator implements StatementPreparator {

	private final String statement;
	private final Page page;
	
	public ComputerGetAllPreparator(String statement, Page page ) {
		this.statement = statement;
		this.page = page;
	}
	
	@Override
	public PreparedStatement prepare(PreparedStatement statement) {
		try {
			statement.setString(1, page.getProperties());
			statement.setString(2, page.getSort().toString());
			statement.setInt(3, page.getSize());
			statement.setInt(4, page.getOffset());
		} catch (SQLException e) {
			throw new PreparationException(e);
		}
		return statement;
	}

	@Override
	public String getStatement() {
		return statement;
	}

}
