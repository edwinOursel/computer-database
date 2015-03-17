package com.excilys.cdb.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.PersistenceException;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.ComputerDatabaseConnection;

public enum CompanyDAO implements DAO<Company, Long> {
	INSTANCE;

	static private final String COMPANY_TABLE = "company";
	
	@Override
	public List<Company> getAll() throws DAOException {
		final List<Company> companies = new ArrayList<>();
		final CompanyMapper companyMapper = new CompanyMapper();		
		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			try (final ResultSet rs = state
					.executeQuery("SELECT * FROM " + COMPANY_TABLE)) {
				while (rs.next()) {
					companies.add(companyMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e);
		}

		return companies;
	}
}
