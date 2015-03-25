package com.excilys.cdb.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.cli.Command;
import com.excilys.cdb.cli.ComputerDatabaseContext;
import com.excilys.cdb.cli.ComputerDatabaseScanner;

public class Main {
	public static void main(String[] args) throws Exception {
		ComputerDatabaseScanner scanner = new ComputerDatabaseScanner();
		ComputerDatabaseContext ctx = new ComputerDatabaseContext(scanner);
		
		Logger logger = LoggerFactory.getLogger(Main.class);
	    logger.info("Program started");
		while (!scanner.isExit()) {
			Command.getCommand(scanner.getNextToken()).execute(ctx);
		}
		logger.info("Program terminated");
	}

}