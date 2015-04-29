package com.excilys.cdb.main;

import java.awt.List;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.validation.Validator;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.cli.ComputerDatabaseContext;
import com.excilys.cdb.exception.ServiceException;
import com.excilys.cdb.model.Company;
import com.excilys.cdb.model.Computer;
import com.excilys.cdb.service.CompanyService;
import com.excilys.cdb.service.ComputerService;

/**
 * Pattern ApiCommand for the processing of actions.
 */
public enum ApiCommand {
	
	
	
	/**
	 * Retrieve all computers.
	 */
	GET_ALL_COMPUTERS("getAllComputers") {

		@Override
		public String execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			
			logger.info("Computers list printed with ApiCommand getAllComputers");
			WebTarget t = target.path("computer/getAll");
			Builder b = t.request();
			
			String l = b.get(String.class);
			
			//Response i = b.get();
			return l.toString();//target.path("computer/getAll").request().buildGet().invoke().toString();			
		}

	},
	/**
	 * Retrieve all companies.
	 */
	GET_ALL_COMPANIES("getAllCompanies") {

		@Override
		public String execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			
			logger.info("Companies list printed with ApiCommand getAllCompanies");
			return target.path("company/getAll").request(MediaType.APPLICATION_JSON_TYPE).get(List.class).toString();
		}

	},
	/**
	 * Retrieve a computer.
	 */
	GET_BY_ID_COMPUTER("getByIdComputer") {

		@Override
		public String execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.print("Identifier : ");
			ctx.setComputerId(Long.valueOf(ctx.getScanner().next()));
			ctx.setComputers(Arrays.asList(computerService.getById(ctx
					.getComputerId())));
			logger.info("Computer printed with ApiCommand getByIdComputer");
			logger.debug(ctx.getComputers().toString());
			return ctx.getComputers().toString();
		}

	},
	/**
	 * Create a new computer.
	 */
	CREATE_COMPUTER("createComputer") {

		@Override
		public String execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			final Computer computer = new Computer();
			populate(ctx, computer);
			ctx.setNewComputer(computer);
			ctx.setComputerId(computerService.create(ctx
					.getNewComputer()));
			if (ctx.getComputerId() > 0) {
				logger.info("Successful attempt to create a computer with ApiCommand createComputer");
				logger.debug(ctx.getComputers().toString());
				return "Successfully created";
			} else {

				logger.info("Failed attempt to create a computer with ApiCommand createComputer");
				logger.debug(String.valueOf(ctx.getComputerId()));
				return "Failed to create";
			}
			
			
/*Scanner scanner = ctx.getScanner();
			
			Form form = new Form();
			form.param("name", scanner.next());
			form.param("introduced", scanner.next());
			form.param("discontinued", scanner.next());
			form.param("companyId", scanner.next());

			logger.info("Companies list printed with ApiCommand getAllCompanies");
			return target.path("computer/create").request(MediaType.APPLICATION_JSON_TYPE).post(
					Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE), List.class).toString();*/
		}
		
		

	},
	/**
	 * Update a computer.
	 */
	UPDATE_COMPUTER("updateComputer") {

		@Override
		public String execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			System.out.println("Identifier : ");
			final Computer computer = computerService.getById(Long
					.valueOf(ctx.getScanner().next()));
			populate(ctx, computer);
			ctx.setNewComputer(computer);
			computerService.update(ctx.getNewComputer());

			logger.info("Computer changed with ApiCommand createComputer");
			logger.debug(computer.toString());
			return "UPDATED";
		}

	},
	/**
	 * Delete a computer.
	 */
	DELETE_COMPUTER("deleteComputer") {

		@Override
		public String execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			ctx.setComputerId(Long.valueOf(ctx.getScanner().next()));
			computerService.delete(ctx.getComputerId());
			return "Identifier : " + ctx.getComputerId() + "Deleted";
		}

	},
	/**
	 * Print some help.
	 */
	HELP("help") {

		@Override
		public String execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			return ("Legal ApiCommands : getAllComputers, getAllCompanies");
		}

	},
	/**
	 * ApiCommand to terminate a program.
	 */
	EXIT("exit") {

		@Override
		public String execute(ComputerDatabaseContext ctx)
				throws ServiceException {
			if (ctx == null) {
				throw new IllegalArgumentException();
			}
			ctx.setExit(true);
			logger.info("Program terminated with ApiCommand exit");
			return "Program terminated";
		}

	};

	private static Map<String, ApiCommand> ApiCommands;
	static {
		ApiCommands = new HashMap<>();
		for (ApiCommand com : ApiCommand.values()) {
			ApiCommands.put(com.ApiCommandLabel, com);
		}
	}
		
	private static Logger logger = LoggerFactory.getLogger(ApiCommand.class);

	private final String ApiCommandLabel;

	private ApiCommand(String ApiCommandLabel) {
		this.ApiCommandLabel = ApiCommandLabel;
	}
	
	/*
	 * Populate a computer model from answers given by the user.
	 */
	private static void populate(ComputerDatabaseContext ctx, Computer computer) {
		System.out.println("Name : ");
		if (ctx.getScanner().hasNext()) {
			computer.setName(ctx.getScanner().next());
		}
		System.out.println("Introduced :");
		if (ctx.getScanner().hasNext()) {
			final String tok = ctx.getScanner().next();
			final StringBuilder sb = new StringBuilder();
			sb.append(tok).append(" ").append("00:00:00");
//			if (ComputerDatabaseValidator.INSTANCE.validateDateTime(sb
//					.toString())) {
//				DateTimeFormatter formatter = DateTimeFormatter
//						.ofPattern("yyyy-MM-dd HH:mm:ss");
//				LocalDateTime dateTime = LocalDateTime.parse(sb.toString(),
//						formatter);
//				computer.setIntroducedDate(dateTime);
//			}
		}
		System.out.println("Discontinued :");
		if (ctx.getScanner().hasNext()) {
			final String tok = ctx.getScanner().next();
			final StringBuilder sb = new StringBuilder();
			sb.append(tok).append(" ").append("00:00:00");
//			if (ComputerDatabaseValidator.INSTANCE.validateDateTime(sb
//					.toString())) {
//				DateTimeFormatter formatter = DateTimeFormatter
//						.ofPattern("yyyy-MM-dd HH:mm:ss");
//				LocalDateTime dateTime = LocalDateTime.parse(sb.toString(),
//						formatter);
//				computer.setDiscontinuedDate(dateTime);
//			}
		}
		System.out.println("Company id : ");
		if (ctx.getScanner().hasNext()) {
			Company c = new Company();
			c.setId(Long.valueOf(ctx.getScanner().next()));
			computer.setCompany(c);
		}
	}

	/**
	 * Return a ApiCommand from its textual value.
	 * 
	 * @param ApiCommand Textual ApiCommand
	 * @return The matching ApiCommand
	 */
	public static ApiCommand getCommand(String command) {
		ApiCommand c = ApiCommands.get(command);	
		if (c == null) {
			logger.info("Bad ApiCommand entered, redirected on help");
			c = ApiCommand.HELP;
		}
		return c;
	}
	
	
	@Component
	public static class ReportTypeServiceInjector {
		@Autowired
		private ComputerService computerService;
		
		@Autowired
		private CompanyService companyService;
		
		@Autowired
		private Validator validator;

	    @PostConstruct
	    public void postConstruct() {
	    	ApiCommand.setComputerService(computerService);
	    	ApiCommand.setCompanyService(companyService);
	    	ApiCommand.setValidator(validator);           
	    }
	}
	
	

	public abstract String execute(ComputerDatabaseContext ctx)
			throws ServiceException;

	private static CompanyService companyService;
	private static Validator validator;
	private static ComputerService computerService;
	
	private static WebTarget target = ClientBuilder.newClient().target("http://localhost:8080").path("api");
	
	public static void setCompanyService(CompanyService companyService) {
		ApiCommand.companyService = companyService;
	}

	public static void setValidator(Validator validator) {
		ApiCommand.validator = validator;
	}

	public static void setComputerService(ComputerService computerService) {
		ApiCommand.computerService = computerService;	
	}
}
