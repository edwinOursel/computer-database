package com.excilys.cdb.exception;

public class ServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;


	public ServiceException(DAOException e) {
		super(e);
	}
}
