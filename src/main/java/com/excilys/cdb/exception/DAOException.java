package com.excilys.cdb.exception;

public class DAOException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(Exception e) {
		super(e);
	}
}
