package de.seven.fate.converter;

import junit.framework.Assert;
import org.junit.Test;

import java.net.URL;

import static org.junit.Assert.*;

/**
 * Created by Mario on 05.04.2016.
 */
public class URLConverterTest {

    URLConverter sut = new URLConverter();

    @Test
    public void convertToDatabaseColumn() throws Exception {
        String actual = sut.convertToDatabaseColumn(new URL("http://7fate.de"));
        Assert.assertEquals("http://7fate.de", actual);
    }

    @Test
    public void convertToEntityAttribute() throws Exception {
        URL actual = sut.convertToEntityAttribute("http://7fate.de");
        Assert.assertEquals(new URL("http://7fate.de"), actual);
    }

}