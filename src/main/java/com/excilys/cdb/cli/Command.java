package com.excilys.cdb.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;
import com.excilys.cdb.validation.ComputerDatabaseValidator;

/**
 * Pattern command for the processing of actions.
 */
public enum Command {
	
	/**
	 * Retrieve all computers.
	 */
	GET_ALL_COMPUTERS("getAllComputers") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			ctx.setComputers(ComputerService.INSTANCE.getAll());
			System.out.println(ctx.getComputers());
			logger.info("Computers list printed with command getAllComputers");
			logger.debug(ctx.getComputers().toString());
		}

	},
	/**
	 * Retrieve all companies.
	 */
	GET_ALL_COMPANIES("getAllCompanies") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			ctx.setCompanies(CompanyService.INSTANCE.getAll());
			System.out.println(ctx.getCompanies());
			logger.info("Companies list printed with command getAllCompanies");
			logger.debug(ctx.getCompanies().toString());
		}

	},
	/**
	 * Retrieve a computer.
	 */
	GET_BY_ID_COMPUTER("getByIdComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.print("Identifier : ");
			ctx.setComputerId(Long.valueOf(ctx.getScanner().getNextToken()));
			ctx.setComputers(Arrays.asList(ComputerService.INSTANCE.getById(ctx
					.getComputerId())));
			System.out.println(ctx.getComputers());
			logger.info("Computer printed with command getByIdComputer");
			logger.debug(ctx.getComputers().toString());
		}

	},
	/**
	 * Create a new computer.
	 */
	CREATE_COMPUTER("createComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			final Computer computer = new Computer();
			populate(ctx, computer);
			ctx.setNewComputer(computer);
			ctx.setComputerId(ComputerService.INSTANCE.create(ctx
					.getNewComputer()));
			if (ctx.getComputerId() > 0) {
				System.out.println("Successfully created");
				logger.info("Successful attempt to create a computer with command createComputer");
				logger.debug(ctx.getComputers().toString());
			} else {
				System.out.println("Failed to create");
				logger.info("Failed attempt to create a computer with command createComputer");
				logger.debug(String.valueOf(ctx.getComputerId()));
			}
		}

	},
	/**
	 * Update a computer.
	 */
	UPDATE_COMPUTER("updateComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.println("Identifier : ");
			final Computer computer = ComputerService.INSTANCE.getById(Long
					.valueOf(ctx.getScanner().getNextToken()));
			populate(ctx, computer);
			ctx.setNewComputer(computer);
			ComputerService.INSTANCE.update(ctx.getNewComputer());
			System.out.println("UPDATED");
			logger.info("Computer changed with command createComputer");
			logger.debug(computer.toString());
		}

	},
	/**
	 * Delete a computer.
	 */
	DELETE_COMPUTER("deleteComputer") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.print("Identifier : ");
			ctx.setComputerId(Long.valueOf(ctx.getScanner().getNextToken()));
			ComputerService.INSTANCE.delete(ctx.getComputerId());
			System.out.println("Deleted");
		}

	},
	/**
	 * Print some help.
	 */
	HELP("help") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.print("Legal commands : ");
			System.out.print("-getAllComputers");
			System.out.print("-getAllCompanies");
		}

	},
	/**
	 * Command to terminate a program.
	 */
	EXIT("exit") {

		@Override
		public void execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.println("Program terminated");
			ctx.getScanner().setExit(true);
			logger.info("Program terminated with command exit");
		}

	};

	private static Map<String, Command> commands;
	static {
		commands = new HashMap<>();
		for (Command com : Command.values()) {
			commands.put(com.commandLabel, com);
		}
	}
		
	private static Logger logger = LoggerFactory.getLogger(Command.class);

	private final String commandLabel;

	private Command(String commandLabel) {
		this.commandLabel = commandLabel;
	}
	
	/*
	 * Populate a computer model from answers given by the user.
	 */
	private static void populate(ComputerDatabaseContext ctx, Computer computer) {
		System.out.println("Name : ");
		if (ctx.getScanner().hasNextToken()) {
			computer.setName(ctx.getScanner().getNextToken());
		}
		System.out.println("Introduced :");
		if (ctx.getScanner().hasNextToken()) {
			final String tok = ctx.getScanner().getNextToken();
			final StringBuilder sb = new StringBuilder();
			sb.append(tok).append(" ").append("00:00:00");
			if (ComputerDatabaseValidator.INSTANCE.validateDate(sb
					.toString())) {
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(sb.toString(),
						formatter);
				computer.setIntroduced(dateTime);
			}
		}
		System.out.println("Discontinued :");
		if (ctx.getScanner().hasNextToken()) {
			final String tok = ctx.getScanner().getNextToken();
			final StringBuilder sb = new StringBuilder();
			sb.append(tok).append(" ").append("00:00:00");
			if (ComputerDatabaseValidator.INSTANCE.validateDate(sb
					.toString())) {
				DateTimeFormatter formatter = DateTimeFormatter
						.ofPattern("yyyy-MM-dd HH:mm:ss");
				LocalDateTime dateTime = LocalDateTime.parse(sb.toString(),
						formatter);
				computer.setDiscontinued(dateTime);
			}
		}
		System.out.println("Company id : ");
		if (ctx.getScanner().hasNextToken()) {
			Company c = new Company();
			c.setId(Long.valueOf(ctx.getScanner().getNextToken()));
			computer.setCompany(c);
		}
	}

	/**
	 * Return a command from its textual value.
	 * 
	 * @param command Textual command
	 * @return The matching command
	 */
	public static Command getCommand(String command) {
		Command c = commands.get(command);	
		if (c == null) {
			logger.info("Bad command entered, redirected on help");
			c = Command.HELP;
		}
		return c;
	}

	public abstract void execute(ComputerDatabaseContext ctx)
			throws ServiceException;
}