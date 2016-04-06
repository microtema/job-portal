package de.seven.fate.appointment.model.builder;

import de.seven.fate.appointment.model.Appointment;
import de.seven.fate.model.builder.AbstractModelBuilder;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mario on 05.04.2016.
 */
public class AppointmentBuilder extends AbstractModelBuilder<Appointment> {

    @Override
    public Appointment min() {

        Appointment min = super.min();

        min.setId(null);

        min.setCreatedDate(null);
        min.setUpdateDate(null);
        min.setVersion(null);

        min.setDate(DateUtils.truncate(new Date(), Calendar.DATE));
        min.setTime(new Time(System.currentTimeMillis()));

        return min;
    }
}
