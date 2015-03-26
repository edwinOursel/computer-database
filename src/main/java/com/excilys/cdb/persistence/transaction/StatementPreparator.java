package com.excilys.cdb.persistence.transaction;

import java.sql.PreparedStatement;

public interface StatementPreparator {	
	PreparedStatement prepare(PreparedStatement statement);
	String getStatement();
}
