package com.excilys.cdb.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.PersistenceException;
import com.excilys.cdb.exception.PreparationException;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDatabaseConnectionFactory;

@Component
public class ComputerDAO implements DAO<Computer, Long> {
	
	static private final String COMPUTER_TABLE = "computer";
	static private final String COMPANY_TABLE = "company";
	
	@Autowired
	private ComputerDatabaseConnectionFactory cf;
	
	
	/**
     * Number of computers in the database.
     *
     * @return Number of entities
     */
    public int count() {
        final String sql = "SELECT COUNT(*) FROM " + COMPUTER_TABLE;
        try (final Statement state = cf.getConnection().createStatement()) {
            final ResultSet rs = state.executeQuery(sql);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return 0;
    }
	
	@Override
	public List<Computer> getAll() throws DAOException {
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();

		try (final Statement state = cf.getConnection().createStatement()) {
			try (final ResultSet rs = state
					.executeQuery("SELECT * FROM " + COMPUTER_TABLE + " compu LEFT OUTER JOIN " 
							+ COMPANY_TABLE + " compa ON compu.company_id = compa.id")) {
				while (rs.next()) {
					computers.add(computerMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e);
		}

		return computers;
	}
	
	public List<Computer> getAll(Page page) throws DAOException {
		final List<Computer> computers = new ArrayList<>();
		final ComputerMapper computerMapper = new ComputerMapper();
		PreparedStatement statement = null;
		try {
			statement = cf.getConnection().prepareStatement("SELECT * FROM " + COMPUTER_TABLE + " compu LEFT OUTER JOIN "
				+ COMPANY_TABLE + " compa ON compu.company_id = compa.id ORDER BY ? ? LIMIT ? OFFSET ?");
			statement.setString(1, page.getProperties());
			statement.setString(2, page.getSort().toString());
			statement.setInt(3, page.getSize());
			statement.setInt(4, page.getOffset());
		} catch (SQLException e1) {
			throw new DAOException(e1);
		}			
		try (final ResultSet rs = statement.executeQuery()) {
			while (rs.next()) {
				computers.add(computerMapper.rowMap(rs));
			}
		} catch (PreparationException | SQLException e) {
			throw new DAOException(e);
		}
		return computers;
	}

	@Override
	public Computer getById(Long id) throws DAOException {
		final ComputerMapper computerMapper = new ComputerMapper();
		final String sql = "SELECT * FROM "+ COMPUTER_TABLE +" compu LEFT OUTER JOIN "
				+ COMPANY_TABLE + " compa ON compu.company_id = compa.id WHERE compu.id = ?";

		try (final PreparedStatement pStatement = cf.getConnection().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			try (final ResultSet rs = pStatement.executeQuery()) {
				if (rs.first()) {
					return computerMapper.rowMap(rs);
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e);
		}

		return null;
	}

	@Override
	public Long create(Computer entity) throws DAOException {
		final String sql = "INSERT INTO " + COMPUTER_TABLE + " VALUES (?, ?, ?, ?, ?)";

		try (final PreparedStatement pStatement = cf.getConnection().prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
			pStatement.setObject(1, null);
			if (entity.getName() != null) {
				pStatement.setString(2, entity.getName());
			}
			if (entity.getIntroducedDate() != null) {
				pStatement.setTimestamp(3,
						Timestamp.valueOf(entity.getIntroducedDate()));
			} else {
				pStatement.setTimestamp(3, null);
			}
			if (entity.getDiscontinuedDate() != null) {
				pStatement.setTimestamp(4,
						Timestamp.valueOf(entity.getDiscontinuedDate()));
			} else {
				pStatement.setTimestamp(4, null);
			}
			if (entity.getCompany() == null) {
				pStatement.setObject(5, null);
			} else {
				pStatement.setLong(5, entity.getCompany().getId());
			}
			pStatement.execute();
			
			try (final ResultSet generatedKeys = pStatement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					return generatedKeys.getLong(1);
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e);
		}
		
		return null;
	}

	@Override
	public void update(Computer entity) throws DAOException {
		final String sql = "UPDATE " + COMPUTER_TABLE + " SET name = ?, introduced = ?, "
				+ "discontinued = ?, company_id = ? WHERE id = ?";
		try (final PreparedStatement pStatement = cf.getConnection().prepareStatement(sql)) {
			if (entity.getName() != null) {
				pStatement.setString(1, entity.getName());
			}
			if (entity.getIntroducedDate() != null) {
				pStatement.setTimestamp(2,
						Timestamp.valueOf(entity.getIntroducedDate()));
			} else {
				pStatement.setTimestamp(2, null);
			}
			if (entity.getDiscontinuedDate() != null) {
				pStatement.setTimestamp(3,
						Timestamp.valueOf(entity.getDiscontinuedDate()));
			} else {
				pStatement.setTimestamp(3, null);
			}
			if (entity.getCompany() == null) {
				pStatement.setObject(4, null);
			} else {
				pStatement.setLong(4, entity.getCompany().getId());
			}
			pStatement.setLong(5, entity.getId());
			pStatement.execute();
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Long id) throws DAOException {
		final String sql = "DELETE FROM " + COMPUTER_TABLE + " WHERE id = ?";
		try (final PreparedStatement pStatement = cf.getConnection().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			pStatement.execute();
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e);
		}
	}

}
