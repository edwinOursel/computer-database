package com.excilys.cdb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import com.excilys.cdb.model.Company;
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
			computer.setIntroducedDate(t.toLocalDateTime());	
		}
		t = res.getTimestamp("discontinued");
		if (t != null) {
			computer.setDiscontinuedDate(t.toLocalDateTime());
		}
		final Long companyId = res.getLong("company_id");
		if (companyId > 0) {
			final Company company = new Company();
			company.setId(companyId);
			company.setName(res.getString("compa.name"));
			computer.setCompany(company);
		}
		return computer;
	}

}
