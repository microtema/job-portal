package de.seven.fate.address.builder;

import de.seven.fate.address.model.Address;
import de.seven.fate.model.builder.AbstractModelBuilder;

import javax.inject.Inject;

/**
 * Created by Mario on 05.04.2016.
 */
public class AddressBuilder extends AbstractModelBuilder<Address> {

    @Override
    public Address min() {
        Address min = super.min();

        min.setId(null);

        min.setCreatedDate(null);
        min.setUpdateDate(null);
        min.setVersion(null);

        return min;
    }
}
