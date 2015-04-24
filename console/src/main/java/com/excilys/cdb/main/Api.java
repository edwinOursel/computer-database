package com.excilys.cdb.main;

import java.util.Scanner;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.cli.ComputerDatabaseContext;

@Component
@Path("")
public class Api {
	
	@Autowired
	private ComputerDatabaseContext ctx;
	
	@POST
	@Consumes("text/plain")
	public Response savePayment(String request) {
		Scanner scanner = new Scanner(request);
		ctx.setScanner(scanner);
		return Response.status(200).entity(ApiCommand.getCommand(scanner.next()).execute(ctx)).build(); 
	}

}
