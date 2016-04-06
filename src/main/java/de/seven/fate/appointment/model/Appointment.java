package de.seven.fate.appointment.model;

import de.seven.fate.dao.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;
import java.util.Objects;

/**
 * Created by Mario on 05.04.2016.
 */
@Entity
public class Appointment extends BaseEntity<Long> {

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date date;

    @NotNull
    //@Temporal(TemporalType.TIME)
    private Time time;

    @NotNull
    private String location;

    private String appointed;

    private String purpose;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAppointed() {
        return appointed;
    }

    public void setAppointed(String appointed) {
        this.appointed = appointed;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(date, that.date) &&
                Objects.equals(time.toLocalTime(), that.time.toLocalTime()) &&
                Objects.equals(location, that.location) &&
                Objects.equals(appointed, that.appointed) &&
                Objects.equals(purpose, that.purpose);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, time, location, appointed, purpose);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("date", date)
                .append("time", time)
                .append("location", location)
                .append("appointed", appointed)
                .append("purpose", purpose)
                .toString();
    }
}

