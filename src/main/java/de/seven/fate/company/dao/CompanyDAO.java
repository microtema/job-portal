package de.seven.fate.company.dao;

import de.seven.fate.address.dao.AddressDAO;
import de.seven.fate.copmany.model.Company;
import de.seven.fate.dao.AbstractEntityDAO;
import de.seven.fate.person.model.Person;

import javax.inject.Inject;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by Mario on 07.04.2016.
 */
public class CompanyDAO extends AbstractEntityDAO<Company, Long> {

    private final AddressDAO addressDAO;

    @Inject
    public CompanyDAO(AddressDAO addressDAO) {
        this.addressDAO = addressDAO;
    }

    @Override
    protected void saveImpl(Company entity) {

        entity.setAddress(addressDAO.saveOrUpdate(entity.getAddress()));

        super.saveImpl(entity);
    }

    @Override
    public Company update(Company entity) {
        notNull(entity);

        entity.setAddress(addressDAO.saveOrUpdate(entity.getAddress()));

        return super.update(entity);
    }

    @Override
    protected void removeImpl(Company entity) {

        addressDAO.remove(entity.getAddress());

        entity.setAddress(null);

        super.removeImpl(entity);
    }
}
