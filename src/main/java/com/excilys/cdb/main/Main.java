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
		

		
	
	}/*
	public static void main( String args[] ) {

	 //search("a12 b8 55 c93 86 2s " , "\\d\\w"); // 1

	 //search("a b8 93 c" , ".*\\d"); // 2

	 search("a b8 93 c" , "\\d.*?"); // 3

	 //search("a b8 93 c" , "\\w.*\\d"); // 4

	 }

	 public static void search(String mat , String pat) {

	 Pattern p = Pattern.compile(pat);

	 Matcher m = p.matcher(mat);

	 while(m.find()) {

	 System.out.print(m.group()+";");

	 }

	 }*/

}