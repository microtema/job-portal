package de.seven.fate.job.model.builder;

import de.seven.fate.job.model.JobEntry;
import de.seven.fate.job.vo.JobEntryVO;
import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.model.util.CollectionUtil;
import de.seven.fate.salary.model.JobEntryState;

/**
 * Created by Mario on 06.04.2016.
 */
public class JobEntryVOBuilder extends AbstractModelBuilder<JobEntryVO> {

    @Override
    public JobEntryVO min() {
        JobEntryVO min = super.min();
        min.setId(null);

        min.setState(CollectionUtil.random(JobEntryState.values()).name());

      //  min.setHumanResource(personBuilder.random());
      //  min.setJobHunter(personBuilder.random());
      //  min.setAppointments(appointmentBuilder.list(1));
      //  min.setLabels(tagEntryBuilder.set(1));
       // min.setSalary(salaryBuilder.random());

        return min;
    }
}
