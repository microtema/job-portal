package de.seven.fate.rest;

import de.seven.fate.job.resource.JobEntryResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Initialization of all REST services.
 */
@ApplicationPath("/rest")
public class RestApplication extends Application {

    private static final Set<Class<?>> classSet = new HashSet<Class<?>>(Arrays.asList(JobEntryResource.class));

    @Override
    public Set<Class<?>> getClasses() {
        return classSet;
    }
}