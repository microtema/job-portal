package de.seven.fate.job.resource;

import de.seven.fate.job.facade.JobEntryFacade;
import de.seven.fate.job.vo.JobEntryVO;
import de.seven.fate.rest.interceptor.RestErrorInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Logger;

/**
 * Created by Mario on 06.04.2016.
 */
@Stateless
@Path("/job")
@Produces(MediaType.APPLICATION_JSON)
@Interceptors(RestErrorInterceptor.class)
public class JobEntryResource {

    private static final Logger logger = Logger.getLogger(JobEntryResource.class.getName());

    @Inject
    private JobEntryFacade facade;

    @GET
    public Response getJobEntries(@QueryParam("start") @DefaultValue(value = "-1") int startPosition, @QueryParam("offset") @DefaultValue(value = "-1") int maxResult) {
        logger.info("get job entries");

        return Response.ok(facade.getJobEntries(startPosition, maxResult)).build();
    }

    @GET
    @Path("/{id}")
    public Response getJobEntry(@PathParam("id") Long id) {
        logger.info("get job by: "+id);

        return Response.ok(facade.getJobEntry(id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteJobEntry(@PathParam("id") Long id) {
        logger.info("delete job by: "+id);

        return Response.ok(facade.deleteJobEntry(id)).build();
    }

    @POST
    public Response createJobEntry(JobEntryVO jobEntry) {
        logger.info("create job entries");

        return Response.ok(facade.createJobEntry(jobEntry)).build();
    }

    @PUT
    public Response updateJobEntry(JobEntryVO jobEntry) {
        logger.info("create job entries");

        return Response.ok(facade.updateJobEntry(jobEntry)).build();
    }

    @GET
    @Path("/status")
    public Response getJobEntryStatus() {
        logger.info("get all job status");

        return Response.ok(facade.getJobEntryStatus()).build();
    }
}
