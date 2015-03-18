package com.excilys.cdb.persistence.dao;

import java.io.Serializable;
import java.util.List;

import com.excilys.cdb.exception.DAOException;

/**
 * @param <T> The entity to manage.
 * @param <I> The id type.
 * @inv
 *     getAll() != null && 0 <= getAll().size()
 */
public interface DAO<T, I extends Serializable> {
	/**
	 * Retrieve all entities.
	 * 
	 * @return Entities
	 * @throws DAOException
	 */
	default List<T> getAll() throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Retrieve entity by its identifier.
	 * 
	 * @pre id != null
	 * @param id Identifier
	 * @return The matching entity
	 * @throws DAOException
	 */
	default T getById(I id) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Create a new entity.
	 * 
	 * @pre entity != null
	 * @param entity
	 * @return The identifier of the entity
	 * @throws DAOException
	 */
	default I create(T entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Update the entity.
	 * 
	 * @pre entity != null
	 * @param entity Entity to update
	 * @throws DAOException
	 */
	default void update(T entity) throws DAOException {
		throw new UnsupportedOperationException();
	}

	/**
	 * Delete a entity by its identifier.
	 * 
	 * @pre id != null
	 * @param id Identifier
	 * @throws DAOException
	 */
	default void delete(I id) throws DAOException {
		throw new UnsupportedOperationException();
	}
}
