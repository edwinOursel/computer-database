package com.excilys.cdb.cli;

import java.util.List;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

public class ComputerDatabaseContext {
	private List<Computer> computers;
	private List<Company> companies;
	private Computer newComputer;
	private long computerId;
	private ComputerDatabaseScanner scanner;
	
	public ComputerDatabaseContext() {}
	
	public ComputerDatabaseContext(ComputerDatabaseScanner scanner) {
		this.scanner = scanner;
	}
	
	public ComputerDatabaseScanner getScanner() {
		return scanner;
	}

	public List<Computer> getComputers() {
		return computers;
	}
	
	public List<Company> getCompanies() {
		return companies;
	}
	
	public long getComputerId() {
		return computerId;
	}
	
	public Computer getNewComputer() {
		return newComputer;
	}

	public void setComputers(List<Computer> computers) {
		this.computers = computers;
	}
	
	public void setCompanies(List<Company> companies) {
		this.companies = companies;
	}

	public void setComputerId(long computerId) {
		this.computerId = computerId;
	}

	public void setNewComputer(Computer newComputer) {
		this.newComputer = newComputer;
	}

	public void setScanner(ComputerDatabaseScanner scanner) {
		this.scanner = scanner;
	}
	
	
}
