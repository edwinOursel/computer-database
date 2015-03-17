package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.excilys.cdb.model.Computer;

public class ComputerMapper implements Mapper<Computer> {

	@Override
	public Computer rowMap(ResultSet res) throws SQLException {
		if (res == null) {
			throw new IllegalArgumentException();
		}
		final Computer computer = new Computer();
		computer.setId(res.getLong("id"));
		computer.setName(res.getString("name"));
		Timestamp t = res.getTimestamp("introduced");
		if (t != null) {
			computer.setIntroduced(t.toLocalDateTime());	
		}
		t = res.getTimestamp("discontinued");
		if (t != null) {
			computer.setDiscontinued(t.toLocalDateTime());
		}
		computer.setCompanyId(res.getLong("company_id"));
		return computer;
	}

}
