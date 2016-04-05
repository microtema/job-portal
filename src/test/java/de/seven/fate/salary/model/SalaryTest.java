package de.seven.fate.salary.model;

import de.seven.fate.address.builder.AddressBuilder;
import de.seven.fate.address.model.Address;
import de.seven.fate.dao.BaseEntity;
import de.seven.fate.person.model.Person;
import de.seven.fate.person.model.builder.PersonBuilder;
import de.seven.fate.salary.model.builder.SalaryBuilder;
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

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by Mario on 05.04.2016.
 */

public class SalaryTest {

    Salary sut;

    SalaryBuilder builder = new SalaryBuilder();

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
    public void minShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("min").getAnnotation(NotNull.class));
    }

    @Test
    public void maxShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("max").getAnnotation(NotNull.class));
    }

    @Test
    public void testEquals() throws Exception {
        Salary clone = SerializationUtils.clone(sut);
        assertEquals(sut, clone);
    }

    @Test
    public void testHashCode() throws Exception {
        Salary clone = SerializationUtils.clone(sut);
        assertEquals(sut.hashCode(), clone.hashCode());
    }

    @Test
    public void shouldNotEquals() throws Exception {
        Salary clone = SerializationUtils.clone(sut);
        clone.setMax(BigDecimal.ONE);
        assertFalse(sut.equals(clone));
    }

    @Test
    public void shouldNotHashCode() throws Exception {
        Salary clone = SerializationUtils.clone(sut);
        clone.setMin(BigDecimal.TEN);
        assertFalse(sut.hashCode() == clone.hashCode());
    }

}
