package de.seven.fate.job.model.builder;

import de.seven.fate.job.model.JobEntry;
import de.seven.fate.appointment.model.builder.AppointmentBuilder;
import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.person.model.builder.PersonBuilder;
import de.seven.fate.salary.model.builder.SalaryBuilder;
import de.seven.fate.tag.model.builder.TagEntryBuilder;

import javax.inject.Inject;

/**
 * Created by Mario on 05.04.2016.
 */
public class JobEntryBuilder extends AbstractModelBuilder<JobEntry> {

    private final PersonBuilder personBuilder;
    private final AppointmentBuilder appointmentBuilder;
    private final TagEntryBuilder tagEntryBuilder;
    private final SalaryBuilder salaryBuilder;

    @Inject
    public JobEntryBuilder(PersonBuilder personBuilder, AppointmentBuilder appointmentBuilder, TagEntryBuilder tagEntryBuilder, SalaryBuilder salaryBuilder) {
        this.personBuilder = personBuilder;
        this.appointmentBuilder = appointmentBuilder;
        this.tagEntryBuilder = tagEntryBuilder;
        this.salaryBuilder = salaryBuilder;
    }

    @Override
    public JobEntry min() {
        JobEntry min = super.min();

        min.setId(null);

        min.setCreatedDate(null);
        min.setUpdateDate(null);
        min.setVersion(null);

        min.setHumanResource(personBuilder.random());
        min.setJobHunter(personBuilder.random());
        min.setAppointments(appointmentBuilder.list(1));
        min.setLabels(tagEntryBuilder.set(1));
        min.setSalary(salaryBuilder.random());

        return min;
    }
}
