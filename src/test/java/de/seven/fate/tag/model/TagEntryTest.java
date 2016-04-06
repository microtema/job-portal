package de.seven.fate.tag.model;

import de.seven.fate.address.model.Address;
import de.seven.fate.dao.BaseEntity;
import de.seven.fate.tag.model.builder.TagEntryBuilder;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.Entity;

import static org.junit.Assert.*;

/**
 * Created by Mario on 05.04.2016.
 */
public class TagEntryTest {

    TagEntry sut;

    TagEntryBuilder builder = new TagEntryBuilder();

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
    public void testEquals() throws Exception {
        TagEntry clone = SerializationUtils.clone(sut);
        assertEquals(sut, clone);
    }

    @Test
    public void testHashCode() throws Exception {
        TagEntry clone = SerializationUtils.clone(sut);
        assertEquals(sut.hashCode(), clone.hashCode());
    }

    @Test
    public void shouldNotEquals() throws Exception {
        TagEntry clone = SerializationUtils.clone(sut);
        clone.setLabel("foo");
        assertFalse(sut.equals(clone));
    }

    @Test
    public void shouldNotHashCode() throws Exception {
        TagEntry clone = SerializationUtils.clone(sut);
        clone.setLabel("bar");
        assertFalse(sut.hashCode() == clone.hashCode());
    }
}