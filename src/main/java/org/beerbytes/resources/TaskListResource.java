package org.beerbytes.resources;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.beerbytes.auth.AuthRequired;
import org.beerbytes.core.Task;
import org.beerbytes.core.User;
import org.beerbytes.db.TaskDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.codahale.metrics.annotation.Timed;

import io.dropwizard.hibernate.UnitOfWork;

@Path("/task-list")
@Produces(MediaType.APPLICATION_JSON)
public class TaskListResource {
	Logger log = LoggerFactory.getLogger(TaskListResource.class);
	private TaskDAO taskDAO;

	private final Validator validator;

	public TaskListResource(TaskDAO taskDAO, Validator validator) {
		this.taskDAO = taskDAO;
		this.validator = validator;
	}
	
	@GET
	@Timed
	@UnitOfWork
	public List<Task> listTasks(@AuthRequired User user) {
		log.info("listTasks was runned");
		return taskDAO.findAllByUser(user);
	}
	
	@GET
	@Path("/task")
	@Timed
	@UnitOfWork
	public Task getTask(@QueryParam("id") Optional<Long> id, @AuthRequired User user) {
		log.info("getTask was runned");
		
		return taskDAO.findByIdAndUserId(id.orElseThrow(()-> new NotFoundException("id was not entered")), user);
	}
	
	@DELETE
	@Path("/task")
	@Timed
	@UnitOfWork
	public Response deleteTask(@QueryParam("id") Optional<Long> optinalId, @AuthRequired User user) {
		log.info("delete-task was runned");
		Long id = optinalId.orElseThrow(()-> new NotFoundException("id was not entered"));
		taskDAO.deleteByIdAndUserId(id, user);
		return Response.status(Status.ACCEPTED).entity("deleted-task with id="+id).build();
	}
	
	@PUT
	@Path("/task")
	@UnitOfWork
	public Response createOrUpdateTask(Task task, @AuthRequired User user) throws URISyntaxException {
		task.setUser(user);
		Set<ConstraintViolation<Task>> violations = validator.validate(task);

		if (violations.size() > 0) {
			ArrayList<String> validationMessages = new ArrayList<String>();
			for (ConstraintViolation<Task> violation : violations) {
				validationMessages.add(violation.getPropertyPath().toString() + ": " + violation.getMessage());
			}
			return Response.status(Status.BAD_REQUEST).entity(validationMessages).build();
		} else {
			taskDAO.createOrUpdate(task);
			return Response.status(Status.CREATED).entity(task).build();
		}

	}
}
