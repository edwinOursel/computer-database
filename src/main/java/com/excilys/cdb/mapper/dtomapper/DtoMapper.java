package com.excilys.cdb.mapper.dtomapper;

import java.util.List;
import java.util.stream.Collectors;

public interface DtoMapper<T, E> {
	T map(E e);
	E unMap(T t);
	
	default List<T> mapList(List<E> l) {
		return l.stream().map(this::map).collect(Collectors.toList());
	}
	
	default List<E> unMapList(List<T> l) {
		return l.stream().map(this::unMap).collect(Collectors.toList());
	}
}
