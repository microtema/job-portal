package de.seven.fate.person.model;

import de.seven.fate.address.builder.AddressBuilder;
import de.seven.fate.address.model.Address;
import de.seven.fate.dao.BaseEntity;
import de.seven.fate.person.model.builder.PersonBuilder;
import org.apache.commons.lang3.SerializationUtils;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Mario on 05.04.2016.
 */

public class PersonTest {

    Person sut;

    PersonBuilder builder = new PersonBuilder(new AddressBuilder());

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
    public void firstNameShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("firstName").getAnnotation(NotNull.class));
    }

    @Test
    public void lastNameShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("lastName").getAnnotation(NotNull.class));
    }

    @Test
    public void testEquals() throws Exception {
        Person clone = SerializationUtils.clone(sut);
        assertEquals(sut, clone);
    }

    @Test
    public void testHashCode() throws Exception {
        Person clone = SerializationUtils.clone(sut);
        assertEquals(sut.hashCode(), clone.hashCode());
    }

    @Test
    public void shouldNotEquals() throws Exception {
        Person clone = SerializationUtils.clone(sut);
        clone.setFirstName("foo");
        assertFalse(sut.equals(clone));
    }

    @Test
    public void shouldNotHashCode() throws Exception {
        Person clone = SerializationUtils.clone(sut);
        clone.setLastName("foo");
        assertFalse(sut.hashCode() == clone.hashCode());
    }
}
