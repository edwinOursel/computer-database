package com.excilys.cdb.exception;

public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ServiceException(DAOException e) {
		super(e);
	}
}
