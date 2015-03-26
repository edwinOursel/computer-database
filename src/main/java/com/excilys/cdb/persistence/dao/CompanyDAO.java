package com.excilys.cdb.persistence.dao;

import java.sql.PreparedStatement;
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
				.getConnection().createStatement()) {
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
	
	@Override
    public Company getById(Long id) {
        final CompanyMapper companyMapper = new CompanyMapper();
        final String sql = "SELECT * FROM company WHERE id = ?";

        try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
                .getConnection().prepareStatement(sql)) {
            pStatement.setLong(1, id);
            final ResultSet rs = pStatement.executeQuery();
            if (rs.first()) {
                return companyMapper.rowMap(rs);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return null;
    }

    public List<Long> getAllCompaniesId() {
        final List<Long> companies = new ArrayList<>();

        try (final Statement state = ComputerDatabaseConnection.INSTANCE
                .getConnection().createStatement()) {
            final ResultSet rs = state.executeQuery("SELECT id FROM company");
            while (rs.next()) {
                companies.add(rs.getLong("id"));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }

        return companies;
    }
}
