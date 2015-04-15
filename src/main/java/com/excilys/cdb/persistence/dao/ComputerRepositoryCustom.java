package com.excilys.cdb.persistence.dao;

import java.util.List;

import com.excilys.cdb.cli.Page;
import com.excilys.cdb.model.Computer;

public interface ComputerRepositoryCustom {

	List<Computer> findAll(Page page);
	
}
