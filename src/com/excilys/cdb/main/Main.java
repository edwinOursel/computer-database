package com.excilys.cdb.main;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.ComputerDatabaseContext;
import com.excilys.cdb.cli.ComputerDatabaseScanner;

public class Main {
	public static void main(String[] args) throws Exception {
		ComputerDatabaseScanner scanner = new ComputerDatabaseScanner();
		ComputerDatabaseContext ctx = new ComputerDatabaseContext(scanner);
		
		while (!scanner.isExit()) {
			Command.getCommand(scanner.getNextToken()).execute(ctx);
		}
	}
}
