package org.beerbytes.auth;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.core.Response;

import org.beerbytes.TaskerConfiguration;
import org.beerbytes.core.Privilege;
import org.beerbytes.core.User;

import jwt4j.JWTHandler;

public class AuthDynamicFeature implements DynamicFeature
{
    private final TaskerConfiguration configuration;

    private final JWTHandler<User> jwtHandler;

    public AuthDynamicFeature(final TaskerConfiguration configuration,
                              final JWTHandler<User> jwtHandler)
    {
        this.configuration = configuration;
        this.jwtHandler = jwtHandler;
    }

    public void configure(ResourceInfo resourceInfo, FeatureContext featureContext)
    {
        final Method resourceMethod = resourceInfo.getResourceMethod();
        if (resourceMethod != null) {
            Stream.of(resourceMethod.getParameterAnnotations())
                    .flatMap(Arrays::stream)
                    .filter(annotation -> annotation.annotationType().equals(AuthRequired.class))
                    .map(AuthRequired.class::cast)
                    .findFirst()
                    .ifPresent(authRequired -> featureContext.register(getAuthFilter(authRequired.value())));
        }
    }

    private ContainerRequestFilter getAuthFilter(final Privilege requiredPrivilege)
    {
        return containerRequestContext -> {
            final String authHeader = containerRequestContext.getHeaderString(configuration.getAuthHeader());
            if (authHeader == null) {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            final User user;
            try {
                user = jwtHandler.decode(authHeader);
            } catch (Exception e) {
                throw new WebApplicationException(Response.Status.UNAUTHORIZED);
            }
            if (!user.getPrivilege().isPrivileged(requiredPrivilege)) {
                throw new WebApplicationException(Response.Status.FORBIDDEN);
            }
            containerRequestContext.setProperty("user", user);
        };
    }
}