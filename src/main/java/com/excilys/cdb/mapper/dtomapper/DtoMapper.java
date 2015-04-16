package com.excilys.cdb.mapper.dtomapper;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public interface DtoMapper<T, E> {
	T map(E e);
	E unMap(T t);
	
	default Iterable<T> mapList(Iterable<E> l) {
		return StreamSupport.stream(l.spliterator(), true).map(this::map).collect(Collectors.toList());
	}
	
	default Iterable<E> unMapList(Iterable<T> l) {
		return StreamSupport.stream(l.spliterator(), true).map(this::unMap).collect(Collectors.toList());
	}
}
