package org.beerbytes.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;
import java.util.Date;

import org.beerbytes.core.Privilege;
import org.beerbytes.core.Task;
import org.beerbytes.core.User;
import org.beerbytes.core.UserCredentials;
import org.junit.Before;
import org.junit.Rule;

import io.dropwizard.testing.junit.DAOTestRule;

public abstract class AbstractDAOTest {

	@Rule
	public DAOTestRule daoTestRule = DAOTestRule.newBuilder()
		.addEntityClass(Task.class)
		.addEntityClass(User.class)
		.addEntityClass(UserCredentials.class)
	.build();

	protected TaskDAO taskDAO;
	protected UserDAO userDAO;
	protected UserCredentialsDAO userCredentialsDAO;

	protected UserCredentials defaultUserCredentials;
	protected User defaultUser;
	protected Task defaultTask;

	@Before
	public void setUp() throws Exception {
		taskDAO = new TaskDAO(daoTestRule.getSessionFactory());
		userDAO = new UserDAO(daoTestRule.getSessionFactory());
		userCredentialsDAO = new UserCredentialsDAO(daoTestRule.getSessionFactory());
		createTaskWithPersonAndUserCredentials();
	}

	
	private void createTaskWithPersonAndUserCredentials() {
		defaultUserCredentials = daoTestRule.inTransaction(() -> {
			return userCredentialsDAO.createOrUpdate(new UserCredentials("user", "123abc"));
		});  
		
		defaultUser = daoTestRule.inTransaction(() -> { 
			return userDAO.createOrUpdate(new User("Nickname", Privilege.USER, defaultUserCredentials));
		});
		
		Date testDate = new Date();
		Time slackTime = new Time(1);
		defaultTask = daoTestRule.inTransaction(() -> {
			 return taskDAO.createOrUpdate(new Task("Info", testDate, testDate, slackTime, defaultUser));
		});
		
		assertThat(defaultUserCredentials.getId()).isGreaterThan(0);
		assertThat(defaultUser.getId()).isGreaterThan(0);
		assertThat(defaultTask.getId()).isGreaterThan(0);
	}
}
