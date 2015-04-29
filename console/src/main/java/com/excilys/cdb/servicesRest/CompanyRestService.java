package com.excilys.cdb.servicesRest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.persistence.dao.CompanyRepository;

@Component
@Path("/company")
public class CompanyRestService {

	@Autowired
	private CompanyRepository repository;
	
	@GET
	@Path("/getAll")
	@Produces("application/json")
	public Response getAllCompanies() {
		return Response.status(200).entity(repository.findAll().toString()).build(); 
	}
	
}
