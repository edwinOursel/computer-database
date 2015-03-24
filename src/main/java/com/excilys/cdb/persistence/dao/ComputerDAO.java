package com.excilys.cdb.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.exception.DAOException;
import com.excilys.cdb.exception.PersistenceException;
import com.excilys.cdb.mapper.ComputerMapper;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.persistence.ComputerDatabaseConnection;

public enum ComputerDAO implements DAO<Computer, Long> {
	INSTANCE;

	static private final String COMPUTER_TABLE = "computer";
	
	/**
     * Number of computers in the database.
     *
     * @return Number of entities
     */
    public int count() {
        final String sql = "SELECT COUNT(*) FROM " + COMPUTER_TABLE;
        try (final Statement state = ComputerDatabaseConnection.INSTANCE
                .getInstance().createStatement()) {
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

		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			try (final ResultSet rs = state
					.executeQuery("SELECT * FROM " + COMPUTER_TABLE + " compu LEFT OUTER JOIN company"
							+ " compa ON compu.company_id = compa.id")) {
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
		final String sql = "SELECT * FROM " + COMPUTER_TABLE + " compu LEFT OUTER JOIN company"
			+ " compa ON compu.company_id = compa.id"
			+ " ORDER BY ? ? LIMIT ? OFFSET ?";
		//TODO mode preparedstatement
		try (final Statement state = ComputerDatabaseConnection.INSTANCE
				.getInstance().createStatement()) {
			try (final ResultSet rs = state.executeQuery(String.format(sql,
					page.getProperties(), page.getSort(), page.getSize(),
					page.getOffset()))) {
				while (rs.next()) {
					computers.add(computerMapper.rowMap(rs));
				}
			}
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e);
		}

		return computers;
	}

	@Override
	public Computer getById(Long id) throws DAOException {
		final ComputerMapper computerMapper = new ComputerMapper();
		final String sql = "SELECT * FROM "+ COMPUTER_TABLE +" compu LEFT OUTER JOIN company"
				+ " compa ON compu.company_id = compa.id WHERE compu.id = ?";

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
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

		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
			pStatement.setObject(1, null);
			if (entity.getName() != null) {
				pStatement.setString(2, entity.getName());
			}
			if (entity.getIntroduced() != null) {
				pStatement.setTimestamp(3,
						Timestamp.valueOf(entity.getIntroduced()));
			} else {
				pStatement.setTimestamp(3, null);
			}
			if (entity.getDiscontinued() != null) {
				pStatement.setTimestamp(4,
						Timestamp.valueOf(entity.getDiscontinued()));
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
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
			if (entity.getName() != null) {
				pStatement.setString(1, entity.getName());
			}
			if (entity.getIntroduced() != null) {
				pStatement.setTimestamp(2,
						Timestamp.valueOf(entity.getIntroduced()));
			} else {
				pStatement.setTimestamp(2, null);
			}
			if (entity.getDiscontinued() != null) {
				pStatement.setTimestamp(3,
						Timestamp.valueOf(entity.getDiscontinued()));
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
		try (final PreparedStatement pStatement = ComputerDatabaseConnection.INSTANCE
				.getInstance().prepareStatement(sql)) {
			pStatement.setLong(1, id);
			pStatement.execute();
		} catch (SQLException | PersistenceException e) {
			throw new DAOException(e);
		}
	}

}
