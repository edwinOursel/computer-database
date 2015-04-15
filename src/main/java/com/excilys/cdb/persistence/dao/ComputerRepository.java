package com.excilys.cdb.persistence.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.excilys.cdb.model.Computer;


public interface ComputerRepository extends CrudRepository<Computer, Long> {
	
	long count();
	
	List<Computer> findAll();
		
	Computer findById();
	
	Computer findById(Long id);
	
	Computer save(Computer computer);
	
	void delete(Long id);
}

