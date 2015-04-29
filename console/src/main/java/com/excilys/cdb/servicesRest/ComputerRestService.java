package com.excilys.cdb.servicesRest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.excilys.cdb.persistence.dao.CompanyRepository;
import com.excilys.cdb.persistence.dao.ComputerRepository;


@Component
@Path("/computer")
public class ComputerRestService {

	@Autowired
	private ComputerRepository repository;
	
	@GET
	@Path("/getAll")
	@Produces("application/json")
	public Response getAllComputers() {
		return Response.status(200).entity(repository.findAll()).build(); 
	}
	
	@GET
	@Consumes("text/plain")
	@Path("/get")
	public Response getComputerById(@QueryParam(value = "id") Long id) {
		return Response.status(200).entity("computer" + id).build(); 
	}
	
	@POST
	@Consumes("application/json")
	@Path("/create")
	public Response create(String computer) {
		Long id = 0l;
		return Response.status(200).entity("computer" + id + "created").build();
	}
	
	@POST
	@Consumes("application/json")
	@Path("/update")
	public Response update(String computer) {
		Long id = 0l;
		return Response.status(200).entity("computer" + id + "updated").build();
	}
	
	@POST
	@Consumes("application/json")
	@Path("/delete")
	public Response delete(String computer) {
		Long id = 0l;
		return Response.status(200).entity("computer" + id + "deleted").build();
	}
	
	
	
}
