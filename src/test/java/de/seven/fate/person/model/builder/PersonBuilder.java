package de.seven.fate.person.model.builder;

import de.seven.fate.address.builder.AddressBuilder;
import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.person.model.Person;

import javax.inject.Inject;

/**
 * Created by Mario on 05.04.2016.
 */
public class PersonBuilder extends AbstractModelBuilder<Person> {

    private final AddressBuilder addressBuilder;

    @Inject
    public PersonBuilder(AddressBuilder addressBuilder) {
        this.addressBuilder = addressBuilder;
    }

    @Override
    public Person min() {
        Person min = super.min();

        min.setId(null);

        min.setCreatedDate(null);
        min.setUpdateDate(null);
        min.setVersion(null);

        min.setAddress(addressBuilder.min());

        return min;
    }
}
