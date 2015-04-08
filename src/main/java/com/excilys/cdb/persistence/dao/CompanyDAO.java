package com.excilys.cdb.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.PersistenceException;
import com.excilys.cdb.mapper.CompanyMapper;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.persistence.ComputerDatabaseConnectionFactory;

@Component
public class CompanyDAO implements DAO<Company, Long> {

	static private final String COMPANY_TABLE = "company";
	
	@Autowired
	private ComputerDatabaseConnectionFactory cdcf;
	
	@Override
	public List<Company> getAll() throws DAOException {
		final List<Company> companies = new ArrayList<>();
		final CompanyMapper companyMapper = new CompanyMapper();		
		try (final Statement state = cdcf.getConnection().createStatement()) {
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

        try (final PreparedStatement pStatement = cdcf.getConnection().prepareStatement(sql)) {
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

        try (final Statement state = cdcf.getConnection().createStatement()) {
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
