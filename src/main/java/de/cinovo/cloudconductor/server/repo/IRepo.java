package de.cinovo.cloudconductor.server.repo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import de.cinovo.cloudconductor.api.MediaType;

/**
 * Copyright 2014 Hoegernet<br>
 * <br>
 *
 * @author Thorsten Hoeger
 *
 */
public interface IRepo {

	/**
	 * @param file the filename
	 * @return the response
	 */
	@Path("/{file:.*}")
	@GET
	@Produces({MediaType.TEXT_HTML, javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM})
	Response get(@PathParam("file") String file);
	
}
