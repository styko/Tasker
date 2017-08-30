package org.beerbytes.resources;

import java.util.Map;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.beerbytes.core.User;
import org.beerbytes.core.UserCredentials;
import org.beerbytes.db.UserDAO;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;
import jwt4j.JWTHandler;

@Path("/auth")
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource {
	private final JWTHandler<User> jwtHandler;
	private final UserDAO userDAO;

	public AuthResource(final JWTHandler<User> jwtHandler, UserDAO userDAO) {
		this.jwtHandler = jwtHandler;
		this.userDAO = userDAO;
	}

	@POST
	@Path("/login")
	@Timed
	@UnitOfWork
	public String login(@Valid UserCredentials userCredentials) {
		Map<UserCredentials, User> mapOfUsers = userDAO.getMapOfUsers();
		if (mapOfUsers.containsKey(userCredentials)) {
			return jwtHandler.encode(mapOfUsers.get(userCredentials));
		} else {
			throw new WebApplicationException(Response.Status.UNAUTHORIZED);
		}
	}
}