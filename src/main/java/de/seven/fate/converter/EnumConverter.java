package de.seven.fate.converter;

import de.seven.fate.job.vo.JobEntryVO;
import de.seven.fate.model.util.ClassUtil;
import org.jboss.resteasy.util.Types;

import javax.persistence.AttributeConverter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Mario on 06.04.2016.
 */
public class EnumConverter<E extends Enum> implements AttributeConverter<E, String> {

    @Override
    public String convertToDatabaseColumn(E attribute) {

        if (attribute == null) {
            return null;
        }

        return attribute.name();
    }

    @Override
    public E convertToEntityAttribute(String dbData) {

        if (dbData == null) {
            return null;
        }

        Type superclass = getClass().getGenericSuperclass();
        if (!(superclass instanceof ParameterizedType)) {
            throw new RuntimeException("Missing type parameter.");
        }

        ParameterizedType parameterized = (ParameterizedType) superclass;
        Type genericType = parameterized.getActualTypeArguments()[0];
        Class<E> type = (Class<E>) Types.getRawType(genericType);

        return convertToEntityAttribute(dbData, type);
    }

    public E convertToEntityAttribute(String dbData, Class<E> type) {

        if (dbData == null) {
            return null;
        }

        Enum anEnum = E.valueOf(type, dbData);
        return (E) anEnum;
    }
}
