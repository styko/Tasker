package org.beerbytes.health;

import java.util.List;

import org.beerbytes.core.Task;
import org.beerbytes.db.TaskDAO;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseSchemaHealthCheck extends HealthCheck {

	private final TaskDAO taskDAO;

	public DatabaseSchemaHealthCheck(TaskDAO taskDAO) {
		super();
		this.taskDAO = taskDAO;
	}

	@Override
	protected Result check() throws Exception {
		List<Task> tasks = taskDAO.findAll();
		if(tasks.isEmpty()){
			return Result.unhealthy("NO testing data prepared in schema");
		} else {
			return Result.healthy();
		}
	}

}
