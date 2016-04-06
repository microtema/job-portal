package it.de.seven.fate.tag.dao;

import de.seven.fate.model.util.CollectionUtil;
import de.seven.fate.salary.dao.SalaryDAO;
import de.seven.fate.salary.model.Salary;
import de.seven.fate.salary.model.builder.SalaryBuilder;
import de.seven.fate.tag.dao.TagEntryDAO;
import de.seven.fate.tag.model.TagEntry;
import de.seven.fate.tag.model.builder.TagEntryBuilder;
import it.de.seven.fate.util.DeploymentUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
public class TagEntryDAOIT {

    @Inject
    TagEntryDAO sut;

    @Inject
    TagEntryBuilder builder;

    @PersistenceContext
    EntityManager em;

    @Inject
    UserTransaction transaction;

    List<TagEntry> models;


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
        sut.save((TagEntry) null);
    }


    @Test(expected = NullPointerException.class)
    public void shouldThrowNullPointerExceptionOnSaveOrUpdateOnNull() throws Exception {
        sut.saveOrUpdate((TagEntry) null);
    }

    @Test
    public void count() throws Exception {
        assertEquals(models.size(), sut.count().intValue());
    }

    @Test
    public void version() throws Exception {
        models.forEach((model) -> {
           // assertNotNull(sut.get(model).getVersion());
        });
    }

    @Test
    public void createDate() throws Exception {
        models.forEach((model) -> {
          //  assertNotNull(sut.get(model).getCreatedDate());
        });
    }

    @Test
    public void updateDate() throws Exception {
        TagEntry model = builder.random();
        model.setId(CollectionUtil.random(models).getId());
        transactional(() -> sut.saveOrUpdate(model));
      //  assertNotNull(sut.get(model).getUpdateDate());
    }

    @Test
    public void save() throws Exception {
        TagEntry model = builder.random();
        transactional(() -> sut.save(model));
        assertEquals(model, sut.get(model));
    }

    @Test
    public void getByModel() throws Exception {
        TagEntry model = CollectionUtil.random(models);
        assertEquals(model, sut.get(model));
    }

    @Test
    public void update() throws Exception {
        TagEntry model = builder.random();
        model.setId(CollectionUtil.random(models).getId());
        transactional(() -> sut.saveOrUpdate(model));
        assertEquals(model, sut.get(model));
    }

    @Test
    public void saveOrUpdate() throws Exception {
        TagEntry model = builder.random();
        transactional(() -> sut.saveOrUpdate(model));
        assertEquals(model, sut.get(model));
    }

    @Test
    public void remove() throws Exception {
        TagEntry model = CollectionUtil.random(models);
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
        List<TagEntry> entities = sut.list();
        assertEquals(models.size(), entities.size());
    }

    @Test
    public void pagination() throws Exception {
        List<TagEntry> entities = sut.list(0, models.size());
        assertEquals(models.size(), entities.size());
    }

    @Test
    public void getById() throws Exception {
        TagEntry model = CollectionUtil.random(models);
        assertEquals(model, sut.get(model.getId()));
    }

    private void transactional(Runnable runnable) throws Exception {
        transaction.begin();
        em.joinTransaction();

        runnable.run();

        transaction.commit();
    }

}