package com.excilys.cdb.exception;

public class PersistenceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PersistenceException(String message) {
		super(message);
	}
	
	public PersistenceException(Exception e) {
		super(e);
	}
}
