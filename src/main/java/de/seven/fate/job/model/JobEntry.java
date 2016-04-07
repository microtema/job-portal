package de.seven.fate.job.model;

import de.seven.fate.appointment.model.Appointment;
import de.seven.fate.converter.URLConverter;
import de.seven.fate.copmany.model.Company;
import de.seven.fate.dao.BaseEntity;
import de.seven.fate.person.model.Person;
import de.seven.fate.salary.model.JobEntryState;
import de.seven.fate.salary.model.Salary;
import de.seven.fate.tag.model.TagEntry;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.net.URL;
import java.util.*;

/**
 * Created by Mario on 05.04.2016.
 */
@Entity
public class JobEntry extends BaseEntity<Long> {

    @NotNull
    private String title;

    @NotNull
    private String description;

    @OneToOne
    private Company copmany;

    @Convert(converter = URLConverter.class)
    private URL url;

    @NotNull
    @Enumerated(EnumType.STRING)
    private JobEntryState state;

    @OneToOne
    private Salary salary;

    @OneToOne
    private Person humanResource;

    @OneToOne
    private Person jobHunter;

    private int favorite;

    @OneToMany
    private List<Appointment> appointments = new ArrayList<>();

    @ManyToMany
    private Set<TagEntry> labels = new HashSet<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public JobEntryState getState() {
        return state;
    }

    public void setState(JobEntryState state) {
        this.state = state;
    }

    public Salary getSalary() {
        return salary;
    }

    public void setSalary(Salary salary) {
        this.salary = salary;
    }

    public Person getHumanResource() {
        return humanResource;
    }

    public void setHumanResource(Person humanResource) {
        this.humanResource = humanResource;
    }

    public Person getJobHunter() {
        return jobHunter;
    }

    public void setJobHunter(Person jobHunter) {
        this.jobHunter = jobHunter;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<TagEntry> getLabels() {
        return labels;
    }

    public void setLabels(Set<TagEntry> labels) {
        this.labels = labels;
    }

    public Company getCopmany() {
        return copmany;
    }

    public void setCopmany(Company copmany) {
        this.copmany = copmany;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobEntry jobEntry = (JobEntry) o;
        return favorite == jobEntry.favorite &&
                Objects.equals(title, jobEntry.title) &&
                Objects.equals(description, jobEntry.description) &&
                Objects.equals(url, jobEntry.url) &&
                state == jobEntry.state &&
                Objects.equals(salary, jobEntry.salary) &&
                Objects.equals(humanResource, jobEntry.humanResource) &&
                Objects.equals(jobHunter, jobEntry.jobHunter) &&
                Objects.equals(copmany, jobEntry.copmany) &&
                Objects.equals(new ArrayList<>(appointments), new ArrayList<>(jobEntry.appointments)) &&
                Objects.equals(labels, jobEntry.labels);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, url, state, salary, humanResource, jobHunter, favorite, appointments, labels, copmany);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("description", description)
                .append("copmany", copmany)
                .append("url", url)
                .append("state", state)
                .append("salary", salary)
                .append("humanResource", humanResource)
                .append("jobHunter", jobHunter)
                .append("favorite", favorite)
                .append("appointments", appointments)
                .append("labels", labels)
                .toString();
    }
}
