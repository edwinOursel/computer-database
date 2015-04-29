package com.excilys.cdb.cli;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.main.ApiCommand;


public class Main {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		ComputerDatabaseContext ctx = new ComputerDatabaseContext(scanner);
		
		Logger logger = LoggerFactory.getLogger(Main.class);
	    logger.info("Program started");
		while (!ctx.isExit()) {
			System.out.println(ApiCommand.getCommand(scanner.next()).execute(ctx));
		}
		logger.info("Program terminated");
	}

}