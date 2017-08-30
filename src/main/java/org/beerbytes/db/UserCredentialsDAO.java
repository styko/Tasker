package org.beerbytes.db;

import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.beerbytes.core.UserCredentials;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public class UserCredentialsDAO extends AbstractDAO<UserCredentials> {

	public UserCredentialsDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	public UserCredentials findById(Long id) {
		return Optional.ofNullable(get(id)).orElseThrow(() -> new NotFoundException("No such UserCredentials with id=" + id + " exists."));
	}
	
	public UserCredentials createOrUpdate(UserCredentials userCredentials) {
		currentSession().saveOrUpdate(userCredentials);
		return userCredentials;
	}
}
