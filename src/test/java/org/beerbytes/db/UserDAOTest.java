package org.beerbytes.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.beerbytes.core.Privilege;
import org.beerbytes.core.User;
import org.beerbytes.core.UserCredentials;
import org.junit.Test;

public class UserDAOTest extends AbstractDAOTest {
		
	@Test
	public void testFindAll(){
		assertThat(userDAO.findAll().size()).isEqualTo(1);
	}
	
	@Test
	public void testUpdateUser() {
		defaultUser.setPrivilege(Privilege.ADMINISTRATOR);
		userDAO.createOrUpdate(defaultUser);
		assertThat(userDAO.findAll().get(0).getPrivilege()).isEqualTo(Privilege.ADMINISTRATOR);
	}
	
	@Test
	public void testGetMapOfUsers(){
		Map<UserCredentials, User> mapOfUsers = userDAO.getMapOfUsers();
		assertThat(mapOfUsers.size()).isEqualTo(1);
		assertThat(mapOfUsers.get(defaultUserCredentials)).isEqualTo(defaultUser);
	}
}
