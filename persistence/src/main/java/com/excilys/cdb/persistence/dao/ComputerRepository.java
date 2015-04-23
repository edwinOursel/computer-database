package com.excilys.cdb.persistence.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.excilys.cdb.model.Computer;


public interface ComputerRepository extends PagingAndSortingRepository<Computer, Long> {
	
	long count();
	
	List<Computer> findAll();
	
	Page<Computer> findAll(Pageable page);
	
	Computer findById(Long id);
	
	Computer save(Computer computer);
	
	void delete(Long id);
}

