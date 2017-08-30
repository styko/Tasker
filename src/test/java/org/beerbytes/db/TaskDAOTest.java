package org.beerbytes.db;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Time;
import java.util.Date;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.NotFoundException;

import org.beerbytes.core.Privilege;
import org.beerbytes.core.Task;
import org.beerbytes.core.User;
import org.beerbytes.core.UserCredentials;
import org.junit.Test;

public class TaskDAOTest extends AbstractDAOTest {
	@Test
	public void testFindById(){
		assertThat(taskDAO.findById(defaultTask.getId())).isEqualTo(defaultTask);
	}
	
	@Test(expected = NotFoundException.class)
	public void testFindByIdNegative(){
		assertThat(taskDAO.findById(111L));
	}
	
	@Test
	public void testFindAll(){
		assertThat(taskDAO.findAll().size()).isEqualTo(1);
	}
	
	@Test
	public void testDeleteByID() {
		assertThat(taskDAO.findAll().size()).isEqualTo(1);
		daoTestRule.inTransaction(() -> {
			taskDAO.deleteById(defaultTask.getId());
		});
		assertThat(taskDAO.findAll().size()).isEqualTo(0);
	}
	
	@Test
	public void testFindAllByUser(){
		User differentUser = new User("Nickname", Privilege.USER, new UserCredentials("user", "123abc"));
		assertThat(taskDAO.findAllByUser(differentUser)).isEmpty();
		
		assertThat(taskDAO.findAllByUser(defaultUser).get(0)).isEqualTo(defaultTask);
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void testCreateCheckInfoValidation(){
		daoTestRule.inTransaction(() -> {
			return taskDAO.createOrUpdate(new Task("In", new Date(), new Date(), new Time(1), defaultUser));
		});
	}
	
	@Test
	public void testUpdateTask() {
		defaultTask.setInfo("Changed Info");
		taskDAO.createOrUpdate(defaultTask);
		assertThat(taskDAO.findById(defaultTask.getId()).getInfo()).isEqualTo("Changed Info");
	}
}
