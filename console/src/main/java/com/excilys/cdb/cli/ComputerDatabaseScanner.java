package com.excilys.cdb.cli;

import java.util.Scanner;

import org.springframework.stereotype.Component;

@Component
public class ComputerDatabaseScanner {
		
	private final Scanner scanner;
	private boolean exit;
	
	public ComputerDatabaseScanner() {
		scanner = null;
	}
	
	public ComputerDatabaseScanner(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public boolean hasNextToken() {
		return scanner.hasNext();
	}
	
	public String getNextToken() {
		return scanner.hasNext() ? scanner.next() : null; 
	}
	
	public boolean isExit() {
		return exit;
	}
	
	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
