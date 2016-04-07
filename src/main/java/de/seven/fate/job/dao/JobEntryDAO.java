package de.seven.fate.job.dao;

import de.seven.fate.address.dao.AddressDAO;
import de.seven.fate.appointment.dao.AppointmentDAO;
import de.seven.fate.job.model.JobEntry;
import de.seven.fate.dao.AbstractEntityDAO;
import de.seven.fate.person.dao.PersonDAO;
import de.seven.fate.salary.dao.SalaryDAO;
import de.seven.fate.tag.dao.TagEntryDAO;

import javax.inject.Inject;

/**
 * Created by Mario on 05.04.2016.
 */
public class JobEntryDAO extends AbstractEntityDAO<JobEntry, Long> {

    @Inject
    private PersonDAO personDAO;

    @Inject
    private SalaryDAO salaryDAO;

    @Inject
    private TagEntryDAO tagEntryDAO;

    @Inject
    private AppointmentDAO appointmentDAO;

    @Override
    public JobEntry update(JobEntry entity) {

        if (entity.getHumanResource() != null) {
            personDAO.saveOrUpdate(entity.getHumanResource());
        }

        if (entity.getJobHunter() != null) {
            personDAO.saveOrUpdate(entity.getJobHunter());
        }

        if (entity.getSalary() != null) {
            salaryDAO.saveOrUpdate(entity.getSalary());
        }

        tagEntryDAO.saveOrUpdate(entity.getLabels());
        appointmentDAO.saveOrUpdate(entity.getAppointments());

        return super.update(entity);
    }

    @Override
    protected void saveImpl(JobEntry entity) {

        if (entity.getHumanResource() != null) {
            personDAO.saveOrUpdate(entity.getHumanResource());
        }

        if (entity.getJobHunter() != null) {
            personDAO.saveOrUpdate(entity.getJobHunter());
        }

        if (entity.getSalary() != null) {
            salaryDAO.saveOrUpdate(entity.getSalary());
        }

        tagEntryDAO.saveOrUpdate(entity.getLabels());
        appointmentDAO.saveOrUpdate(entity.getAppointments());

        super.saveImpl(entity);
    }

    @Override
    protected void removeImpl(JobEntry entity) {

        if (entity.getHumanResource() != null) {
            personDAO.remove(entity.getHumanResource());
        }

        if (entity.getSalary() != null) {
            salaryDAO.remove(entity.getSalary());
        }

        if (entity.getAppointments() != null) {
            appointmentDAO.remove(entity.getAppointments());
        }

        entity.setHumanResource(null);
        entity.setJobHunter(null);
        entity.setSalary(null);
        entity.setLabels(null);
        entity.setAppointments(null);

        super.removeImpl(entity);
    }

}
