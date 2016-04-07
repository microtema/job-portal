package de.seven.fate.copmany.model;

import de.seven.fate.address.builder.AddressBuilder;
import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.person.model.Person;

import javax.inject.Inject;

/**
 * Created by Mario on 07.04.2016.
 */
public class CompanyBuilder extends AbstractModelBuilder<Company> {

    private final AddressBuilder addressBuilder;

    @Inject
    public CompanyBuilder(AddressBuilder addressBuilder) {
        this.addressBuilder = addressBuilder;
    }

    @Override
    public Company min() {
        Company min = super.min();

        min.setId(null);

        min.setCreatedDate(null);
        min.setUpdateDate(null);
        min.setVersion(null);

        min.setAddress(addressBuilder.min());

        return min;
    }
}
