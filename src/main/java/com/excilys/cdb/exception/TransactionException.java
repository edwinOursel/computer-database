package com.excilys.cdb.exception;

public class TransactionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public TransactionException(Exception e) {
		super(e);
	}
}
