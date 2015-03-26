package com.excilys.cdb.persistence.transaction;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.cdb.exception.TransactionException;

public interface Transaction<E, T> {
	T performOperation(E e) throws TransactionException;
	
	default List<T> performOperations(List<E> l) {
		return l.stream().map(this::performOperation).collect(Collectors.toList());
	}
}
