package org.beerbytes.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beerbytes.core.User;
import org.beerbytes.core.UserCredentials;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public class UserDAO extends AbstractDAO<User> {

	public UserDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<User> findAll() {
		return list(namedQuery("User.findAll"));
	}

	public Map<UserCredentials, User> getMapOfUsers() {
		List<User> list = findAll();

		Map<UserCredentials, User> mapOfUsers = new HashMap<>();

		for (User user : list) {
			mapOfUsers.put(user.getUserCredentials(), user);
		}
		
		return mapOfUsers;
	}
	
	public User createOrUpdate(User user) {
		currentSession().saveOrUpdate(user);
		return user;
	}
}
