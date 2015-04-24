package com.excilys.cdb.cli;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Component;

import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;

@Component
public class ComputerDatabaseContext {
	private List<Computer> computers;
	private List<Company> companies;
	private Computer newComputer;
	private long computerId;
	private Scanner scanner;
	private boolean exit;
	
	public ComputerDatabaseContext() {}
	
	public ComputerDatabaseContext(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public Scanner getScanner() {
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

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}

	public boolean isExit() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
	
	
}
