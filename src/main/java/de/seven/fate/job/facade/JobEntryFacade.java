package de.seven.fate.job.facade;

import de.seven.fate.job.converter.JobEntryToJobEntryVOConverter;
import de.seven.fate.job.converter.JobEntryVOToJobEntryConverter;
import de.seven.fate.job.dao.JobEntryDAO;
import de.seven.fate.job.model.JobEntry;
import de.seven.fate.job.vo.JobEntryVO;
import de.seven.fate.salary.model.JobEntryState;
import org.apache.commons.lang3.Validate;

import javax.ejb.NoSuchEntityException;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Mario on 06.04.2016.
 */
@Stateless
public class JobEntryFacade {

    @Inject
    private JobEntryDAO dao;

    @Inject
    private JobEntryToJobEntryVOConverter converter;

    @Inject
    private JobEntryVOToJobEntryConverter voToJobEntryConverter;

    public List<JobEntryVO> getJobEntries(int startPosition, int maxResult) {

        return converter.convertList(dao.list(startPosition, maxResult));
    }

    public JobEntryVO getJobEntry(Long id) {

        JobEntry jobEntry = dao.get(id);

        if (jobEntry == null) {
            throw new NoSuchEntityException("unable to get JobEntry by Id: " + id);
        }

        return converter.convert(jobEntry);
    }

    public Boolean deleteJobEntry(Long id) {

        dao.remove(id);

        return Boolean.TRUE;
    }

    public JobEntryVO createJobEntry(JobEntryVO jobEntryVO) {

        JobEntry jobEntry = voToJobEntryConverter.convert(jobEntryVO);

        dao.save(jobEntry);

        return converter.convert(jobEntry);
    }

    public JobEntryVO updateJobEntry(JobEntryVO jobEntryVO) {
        Validate.notNull(jobEntryVO);
        Validate.notNull(jobEntryVO.getId());

        JobEntry jobEntry = voToJobEntryConverter.convert(jobEntryVO);

        return converter.convert(dao.update(jobEntry));
    }

    public List<String> getJobEntryStatus() {
        return Arrays.asList(JobEntryState.values()).stream().map(JobEntryState::name).collect(Collectors.toList());
    }
}
