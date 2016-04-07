package it.de.seven.fate.job.resource;

import de.seven.fate.job.dao.JobEntryDAO;
import de.seven.fate.job.model.JobEntry;
import de.seven.fate.job.model.builder.JobEntryBuilder;
import de.seven.fate.job.model.builder.JobEntryVOBuilder;
import de.seven.fate.job.resource.JobEntryResource;
import de.seven.fate.job.vo.JobEntryVO;
import de.seven.fate.rest.interceptor.ErrorMessage;
import de.seven.fate.salary.model.JobEntryState;
import it.de.seven.fate.util.DeploymentUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mario on 06.04.2016.
 */
@RunWith(Arquillian.class)
public class JobResourceIT {

    @Inject
    JobEntryResource sut;

    @Inject
    JobEntryDAO dao;

    @Inject
    JobEntryBuilder builder;

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction transaction;

    List<JobEntry> models;

    GenericType<List<JobEntryVO>> JobEntryVOList = new GenericType<List<JobEntryVO>>() {
    };

    GenericType<List<String>> JobEntryStatusList = new GenericType<List<String>>() {
    };

    JobEntryVOBuilder voBuilder = new JobEntryVOBuilder();

    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentUtil.createDeployment();
    }

    @Test
    @InSequence(1)
    public void setUp() throws Exception {
        models = builder.list();
        transactional(() -> dao.save(models));
    }

    @Test
    @InSequence(100)
    public void tearDown() throws Exception {
        transactional(() -> dao.removeAll());
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void getList(@ArquillianResource URL baseURL) throws Exception {

        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/job").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        ClientResponse<List<JobEntryVO>> clientResponse = request.get(JobEntryVOList);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        List<JobEntryVO> list = clientResponse.getEntity();

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void getPagination(@ArquillianResource URL baseURL) throws Exception {

        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/job?start=0&offset=1").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        ClientResponse<List<JobEntryVO>> clientResponse = request.get(JobEntryVOList);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        List<JobEntryVO> list = clientResponse.getEntity();

        Assert.assertNotNull(list);
        Assert.assertFalse(list.isEmpty());
        Assert.assertEquals(1, list.size());
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void getEntry(@ArquillianResource URL baseURL) throws Exception {

        Long id = 1l;
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/job/" + id).toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        ClientResponse<JobEntryVO> clientResponse = request.get(JobEntryVO.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        JobEntryVO entity = clientResponse.getEntity();

        Assert.assertNotNull(entity);
        Assert.assertEquals(id, entity.getId());
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void deleteEntry(@ArquillianResource URL baseURL) throws Exception {

        Long id = 1l;
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/job/" + id).toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("DELETE");
        ClientResponse<Boolean> clientResponse = request.delete(Boolean.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        Boolean entity = clientResponse.getEntity();

        Assert.assertTrue(entity);
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void createEntry(@ArquillianResource URL baseURL) throws Exception {

        JobEntryVO vo = voBuilder.random();
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/job/").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("POST");
        request.body(MediaType.APPLICATION_JSON, vo);
        ClientResponse<JobEntryVO> clientResponse = request.post(JobEntryVO.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        JobEntryVO entity = clientResponse.getEntity();

        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        Assert.assertEquals(vo, entity);
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void updateEntry(@ArquillianResource URL baseURL) throws Exception {

        JobEntryVO vo = voBuilder.random();
        vo.setId(1l);
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/job/").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("PUT");
        request.body(MediaType.APPLICATION_JSON, vo);
        ClientResponse<JobEntryVO> clientResponse = request.put(JobEntryVO.class);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        JobEntryVO entity = clientResponse.getEntity();

        Assert.assertNotNull(entity);
        Assert.assertNotNull(entity.getId());
        Assert.assertEquals(vo, entity);
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void shouldNotUpdateEntry(@ArquillianResource URL baseURL) throws Exception {

        JobEntryVO vo = voBuilder.random();
        vo.setId(null);
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/job/").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("PUT");
        request.body(MediaType.APPLICATION_JSON, vo);
        ClientResponse<ErrorMessage> clientResponse = request.put(ErrorMessage.class);

        //then
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), clientResponse.getStatus());

        ErrorMessage entity = clientResponse.getEntity();

        Assert.assertNotNull(entity);
    }

    @Test
    @RunAsClient
    @InSequence(2)
    public void getJobEntryStatus(@ArquillianResource URL baseURL) throws Exception {

        JobEntryVO vo = voBuilder.random();
        vo.setId(null);
        //given

        //when
        ClientRequest request = new ClientRequest(new URL(baseURL, "rest/job/status").toExternalForm());
        request.accept(MediaType.APPLICATION_JSON);
        request.setHttpMethod("GET");
        ClientResponse<List<String>> clientResponse = request.get(JobEntryStatusList);

        //then
        assertEquals(Response.Status.OK.getStatusCode(), clientResponse.getStatus());

        List<String> entity = clientResponse.getEntity();

        Assert.assertNotNull(entity);
        Assert.assertEquals(JobEntryState.values().length, entity.size());
        entity.forEach(JobEntryState::valueOf);
    }

    private void transactional(Runnable runnable) throws Exception {
        transaction.begin();
        em.joinTransaction();

        runnable.run();

        transaction.commit();
    }
}