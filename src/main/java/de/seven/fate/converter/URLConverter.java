package de.seven.fate.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;

/**
 * Created by Mario on 05.04.2016.
 */
@Converter
public class URLConverter implements AttributeConverter<URL, String> {

    @Override
    public String convertToDatabaseColumn(URL attribute) {

        if (attribute == null) {
            return null;
        }

        return attribute.toString();
    }

    @Override
    public URL convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        try {
            return new URL(dbData);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
