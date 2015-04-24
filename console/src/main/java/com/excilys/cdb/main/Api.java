package com.excilys.cdb.main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
@Path("/api")
public class Api {
	
	@GET
	public Response savePayment() {
 
		String result = "test";
 
		return Response.status(200).entity(result).build();
 
	}

}
