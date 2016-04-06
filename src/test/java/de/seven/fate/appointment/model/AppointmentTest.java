package de.seven.fate.appointment.model;

import de.seven.fate.address.model.Address;
import de.seven.fate.appointment.model.builder.AppointmentBuilder;
import de.seven.fate.dao.BaseEntity;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import static org.junit.Assert.*;

/**
 * Created by Mario on 05.04.2016.
 */
public class AppointmentTest {

    Appointment sut;

    AppointmentBuilder builder = new AppointmentBuilder();

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
    public void locationShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("location").getAnnotation(NotNull.class));
    }

    @Test
    public void dateShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("date").getAnnotation(NotNull.class));
    }

    @Test
    public void timeShouldBeAnnotated() throws Exception {
        assertNotNull(sut.getClass().getDeclaredField("time").getAnnotation(NotNull.class));
    }

    @Test
    public void testEquals() throws Exception {
        Appointment clone = SerializationUtils.clone(sut);
        assertEquals(sut, clone);
    }

    @Test
    public void testHashCode() throws Exception {
        Appointment clone = SerializationUtils.clone(sut);
        assertEquals(sut.hashCode(), clone.hashCode());
    }

    @Test
    public void shouldNotEquals() throws Exception {
        Appointment clone = SerializationUtils.clone(sut);
        clone.setAppointed("foo");
        assertFalse(sut.equals(clone));
    }

    @Test
    public void shouldNotHashCode() throws Exception {
        Appointment clone = SerializationUtils.clone(sut);
        clone.setPurpose("foo");
        assertFalse(sut.hashCode() == clone.hashCode());
    }

}