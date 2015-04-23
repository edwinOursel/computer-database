package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<T> {
	/**
	 * Maps result set into entity.
	 * @pre res != null
	 * @param res Result to map
	 * @return The mapped entity
	 * @throws SQLException
	 */
	T rowMap(ResultSet res) throws SQLException;
}
