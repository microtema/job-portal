package de.seven.fate.job.model;

import de.seven.fate.address.builder.AddressBuilder;
import de.seven.fate.address.model.Address;
import de.seven.fate.appointment.model.builder.AppointmentBuilder;
import de.seven.fate.dao.BaseEntity;
import de.seven.fate.job.model.builder.JobEntryBuilder;
import de.seven.fate.person.model.builder.PersonBuilder;
import de.seven.fate.salary.model.builder.SalaryBuilder;
import de.seven.fate.tag.model.builder.TagEntryBuilder;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.*;

/**
 * Created by Mario on 05.04.2016.
 */
public class JobEntryTest {

    JobEntry sut;

    JobEntryBuilder builder = new JobEntryBuilder(new PersonBuilder(new AddressBuilder()), new AppointmentBuilder(), new TagEntryBuilder(), new SalaryBuilder());

    @Before
    public void setUp() throws Exception {
        sut = builder.random();
    }

    @Test
    public void testImplementation() throws Exception {
        assertTrue(BaseEntity.class.isAssignableFrom(sut.getClass()));
    }

    @Test
    public void shouldBeEntity() throws Exception {
        assertTrue(Address.class.isAnnotationPresent(Entity.class));
    }

    @Test
    public void titleNameShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("title").getAnnotation(NotNull.class));
    }

    @Test
    public void descriptionShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("description").getAnnotation(NotNull.class));
    }

    @Test
    public void testEquals() throws Exception {
        JobEntry clone = SerializationUtils.clone(sut);
        assertEquals(sut, clone);
    }

    @Test
    public void testHashCode() throws Exception {
        JobEntry clone = SerializationUtils.clone(sut);
        assertEquals(sut.hashCode(), clone.hashCode());
    }

    @Test
    public void shouldNotEquals() throws Exception {
        JobEntry clone = SerializationUtils.clone(sut);
        clone.setTitle("foo");
        assertFalse(sut.equals(clone));
    }

    @Test
    public void shouldNotHashCode() throws Exception {
        JobEntry clone = SerializationUtils.clone(sut);
        clone.setDescription("foo");
        assertFalse(sut.hashCode() == clone.hashCode());
    }

}