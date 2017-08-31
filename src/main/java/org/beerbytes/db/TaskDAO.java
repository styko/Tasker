package org.beerbytes.db;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.beerbytes.core.Task;
import org.beerbytes.core.User;
import org.hibernate.SessionFactory;

import io.dropwizard.hibernate.AbstractDAO;

public class TaskDAO extends AbstractDAO<Task> {

	public TaskDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	public List<Task> findAll() {
		return list(namedQuery("Task.findAll"));
	}
	
	public List<Task> findAllByUser(User user) {
		return list(namedQuery("Task.findAllbyUserId").setLong("id", user.getId()));
	}
	
	public Task findByIdAndUserId(Long id,User user) {
		return uniqueResult(namedQuery("Task.findByIdAndUserId").setLong("id", id).setLong("userid", user.getId()));
	}
	
	public void deleteByIdAndUserId(Long id,User user) {
		currentSession().delete(findByIdAndUserId(id,user));
	}
	
	public Task findById(Long id) {
		return Optional.ofNullable(get(id)).orElseThrow(() -> new NotFoundException("No such task with id=" + id + " exists."));
	}

	public void deleteById(Long id) {
		currentSession().delete(findById(id));
	}

	public Task createOrUpdate(Task task) {
		currentSession().saveOrUpdate(task);
		return task;
	}
}
