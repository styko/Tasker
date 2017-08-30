package org.beerbytes.db;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class UserCredentialsDAOTest extends AbstractDAOTest {
		
	@Test
	public void testUpdateUserCredentials() {
		defaultUserCredentials.setUsername("changedUserName");
		userCredentialsDAO.createOrUpdate(defaultUserCredentials);
		assertThat(userCredentialsDAO.findById(defaultUserCredentials.getId()).getUsername()).isEqualTo("changedUserName");
	}
	
}
