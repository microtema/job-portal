package de.seven.fate.person.dao;

import de.seven.fate.address.dao.AddressDAO;
import de.seven.fate.address.model.Address;
import de.seven.fate.dao.AbstractEntityDAO;
import de.seven.fate.person.model.Person;
import org.apache.commons.lang3.Validate;

import javax.inject.Inject;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by Mario on 05.04.2016.
 */
public class PersonDAO extends AbstractEntityDAO<Person, Long> {

    private final AddressDAO addressDAO;

    @Inject
    public PersonDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    protected void saveImpl(Person entity) {

        entity.setAddress(addressDAO.saveOrUpdate(entity.getAddress()));

        super.saveImpl(entity);
    }

    @Override
    public Person update(Person entity) {
        notNull(entity);

        entity.setAddress(addressDAO.saveOrUpdate(entity.getAddress()));

        return super.update(entity);
    }

    @Override
    protected void removeImpl(Person entity) {

        addressDAO.remove(entity.getAddress());

        entity.setAddress(null);

        super.removeImpl(entity);
    }
}
