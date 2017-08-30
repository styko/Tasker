package org.beerbytes;

import java.util.stream.Stream;

import org.beerbytes.auth.AuthDynamicFeature;
import org.beerbytes.auth.AuthValueFactoryProvider;
import org.beerbytes.core.Task;
import org.beerbytes.core.User;
import org.beerbytes.core.UserCredentials;
import org.beerbytes.db.TaskDAO;
import org.beerbytes.db.UserDAO;
import org.beerbytes.health.DatabaseSchemaHealthCheck;
import org.beerbytes.resources.AuthResource;
import org.beerbytes.resources.TaskListResource;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.jersey.setup.JerseyEnvironment;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import jwt4j.JWTHandler;
import jwt4j.JWTHandlerBuilder;

public class TaskerApplication extends Application<TaskerConfiguration> {

	private final HibernateBundle<TaskerConfiguration> hibernateBundle = new HibernateBundle<TaskerConfiguration>(Task.class, User.class, UserCredentials.class) {
		@Override
		public DataSourceFactory getDataSourceFactory(TaskerConfiguration configuration) {
			return configuration.getDataSourceFactory();
		}
	};

	public static void main(final String[] args) throws Exception {
		new TaskerApplication().run(args);
	}

	@Override
	public String getName() {
		return "tasker";
	}

	@Override
	public void initialize(final Bootstrap<TaskerConfiguration> bootstrap) {
		bootstrap.addBundle(new MigrationsBundle<TaskerConfiguration>() {
			@Override
			public DataSourceFactory getDataSourceFactory(TaskerConfiguration configuration) {
				return configuration.getDataSourceFactory();
			}
		});
		bootstrap.addBundle(hibernateBundle);
	}

	@Override
	public void run(final TaskerConfiguration configuration, final Environment environment) {
		final TaskDAO taskDAO = new TaskDAO(hibernateBundle.getSessionFactory());
		final UserDAO userDAO = new UserDAO(hibernateBundle.getSessionFactory());

		environment.healthChecks().register("", new DatabaseSchemaHealthCheck(taskDAO));

		final JWTHandler<User> jwtHandler = getJwtHandler(configuration);
		final JerseyEnvironment jerseyEnvironment = environment.jersey();

		Stream.of(
				new TaskListResource(taskDAO, environment.getValidator()),
				new AuthResource(jwtHandler, userDAO),
				new AuthDynamicFeature(configuration, jwtHandler),
				new AuthValueFactoryProvider.Binder())
		.forEach(jerseyEnvironment::register);
	}

	private JWTHandler<User> getJwtHandler(TaskerConfiguration taskerConfiguration) {
		return new JWTHandlerBuilder<User>().withSecret(taskerConfiguration.getAuthSalt().getBytes()).withDataClass(User.class).build();
	}

}
