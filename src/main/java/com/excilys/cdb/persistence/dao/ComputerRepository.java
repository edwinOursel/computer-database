package com.excilys.cdb.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.model.Computer;

public interface ComputerRepository extends CrudRepository<Computer, Long> {

	static final String COMPUTER_TABLE = "computer";
	static final String COMPANY_TABLE = "company";
	
	long count();
	
	List<Computer> findAll();
	
	@Query("SELECT compu FROM " + COMPUTER_TABLE + " compu LEFT OUTER JOIN "
			+ COMPANY_TABLE + " compa ON compu.company_id = compa.id ORDER BY ?1.getProperties()"
					+ " ?1.getSort().toString() LIMIT ?1.getSize() OFFSET ?1.getOffset()")
	List<Computer> findAll(Page page);
	
	Computer findById();
	
	Computer findById(Long id);
	
	Computer save(Computer computer);
	
	void delete(Long id);
}
