package it.de.seven.fate.address.dao;

import de.seven.fate.address.builder.AddressBuilder;
import de.seven.fate.address.dao.AddressDAO;
import de.seven.fate.address.model.Address;
import de.seven.fate.model.util.CollectionUtil;
import it.de.seven.fate.util.DeploymentUtil;
import junit.framework.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mario on 05.04.2016.
 */
@RunWith(Arquillian.class)
public class AddressDAOIT {

    @Inject
    AddressDAO sut;

    @Inject
    AddressBuilder builder;

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction transaction;

    List<Address> models;


    @Deployment
    public static WebArchive createDeployment() {
        return DeploymentUtil.createDeployment();
    }

    @Before
    public void setUp() throws Exception {
        models = builder.list();

        transactional(() -> sut.save(models));
    }

    @After
    public void tearDown() throws Exception {
        transactional(() -> sut.removeAll());
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnSaveOnNull() throws Exception {
        sut.save((Address) null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnSaveOrUpdateOnNull() throws Exception {
        sut.saveOrUpdate((Address) null);
    }

    @Test
    public void count() throws Exception {
        assertEquals(models.size(), sut.count().intValue());
    }

    @Test
    public void version() throws Exception {
        models.forEach((model) -> {
            assertNotNull(sut.get(model).getVersion());
        });
    }

    @Test
    public void createDate() throws Exception {
        models.forEach((model) -> {
            assertNotNull(sut.get(model).getCreatedDate());
        });
    }

    @Test
    public void updateDate() throws Exception {
        Address model = builder.random();
        model.setId(CollectionUtil.random(models).getId());
        transactional(() -> sut.saveOrUpdate(model));
        assertNotNull(sut.get(model).getUpdateDate());
    }

    @Test
    public void save() throws Exception {
        Address model = builder.random();
        transactional(() -> sut.save(model));
        assertEquals(model, sut.get(model));
    }

    @Test
    public void getByModel() throws Exception {
        Address model = CollectionUtil.random(models);
        assertEquals(model, sut.get(model));
    }

    @Test
    public void update() throws Exception {
        Address model = builder.random();
        model.setId(CollectionUtil.random(models).getId());
        transactional(() -> sut.saveOrUpdate(model));
        assertEquals(model, sut.get(model));
    }

    @Test
    public void saveOrUpdate() throws Exception {
        Address model = builder.random();
        transactional(() -> sut.saveOrUpdate(model));
        assertEquals(model, sut.get(model));
    }

    @Test
    public void remove() throws Exception {
        Address model = CollectionUtil.random(models);
        transactional(() -> sut.remove(model));
        assertNull(sut.get(model));
        assertEquals(models.size() - 1, sut.count().intValue());
    }

    @Test
    public void removeAll() throws Exception {
        transactional(() -> sut.removeAll());
        assertTrue(sut.list().isEmpty());
    }

    @Test
    public void list() throws Exception {
        List<Address> entities = sut.list();
        assertEquals(models.size(), entities.size());
    }

    @Test
    public void pagination() throws Exception {
        List<Address> entities = sut.list(0, models.size());
        assertEquals(models.size(), entities.size());
    }

    @Test
    public void getById() throws Exception {
        Address model = CollectionUtil.random(models);
        assertEquals(model, sut.get(model.getId()));
    }

    private void transactional(Runnable runnable) throws Exception {
        transaction.begin();
        em.joinTransaction();

        runnable.run();

        transaction.commit();
    }
}
