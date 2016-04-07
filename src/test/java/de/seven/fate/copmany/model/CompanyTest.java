package de.seven.fate.copmany.model;

import de.seven.fate.address.builder.AddressBuilder;
import de.seven.fate.address.model.Address;
import de.seven.fate.dao.BaseEntity;
import de.seven.fate.person.model.Person;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.*;

/**
 * Created by Mario on 07.04.2016.
 */
public class CompanyTest {

    Company sut;

    CompanyBuilder builder = new CompanyBuilder(new AddressBuilder());

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
    public void nameShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("name").getAnnotation(NotNull.class));
    }


    @Test
    public void testEquals() throws Exception {
        Company clone = SerializationUtils.clone(sut);
        assertEquals(sut, clone);
    }

    @Test
    public void testHashCode() throws Exception {
        Company clone = SerializationUtils.clone(sut);
        assertEquals(sut.hashCode(), clone.hashCode());
    }

    @Test
    public void shouldNotEquals() throws Exception {
        Company clone = SerializationUtils.clone(sut);
        clone.setName("foo");
        assertFalse(sut.equals(clone));
    }

    @Test
    public void shouldNotHashCode() throws Exception {
        Company clone = SerializationUtils.clone(sut);
        clone.setName("foo");
        assertFalse(sut.hashCode() == clone.hashCode());
    }
}