package de.seven.fate.salary.model.builder;

import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.salary.model.Salary;

/**
 * Created by Mario on 05.04.2016.
 */
public class SalaryBuilder extends AbstractModelBuilder<Salary> {

    @Override
    public Salary min() {
        Salary min = super.min();

        min.setId(null);

        min.setCreatedDate(null);
        min.setUpdateDate(null);
        min.setVersion(null);

        return min;
    }
}
