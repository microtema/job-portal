package de.seven.fate.job.converter;

import de.seven.fate.converter.EnumConverter;
import de.seven.fate.converter.URLConverter;
import de.seven.fate.job.model.JobEntry;
import de.seven.fate.job.model.builder.JobEntryVOBuilder;
import de.seven.fate.job.vo.JobEntryVO;
import de.seven.fate.salary.model.JobEntryState;
import junit.framework.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Mario on 06.04.2016.
 */
public class JobEntryVOToJobEntryConverterTest {

    JobEntryVOToJobEntryConverter sut = new JobEntryVOToJobEntryConverter(new URLConverter(), new EnumConverter<JobEntryState>());

    JobEntryVOBuilder builder = new JobEntryVOBuilder();
    JobEntryVO vo = builder.random();


    @Test
    public void update() throws Exception {

        JobEntry model = sut.convert(vo);

        Assert.assertNotNull(model);
        Assert.assertEquals(vo.getId(), model.getId());
        Assert.assertEquals(vo.getFavorite(), model.getFavorite());
        Assert.assertEquals(vo.getDescription(), model.getDescription());
        Assert.assertEquals(vo.getTitle(), model.getTitle());
        Assert.assertEquals(vo.getUrl(), model.getUrl().toString());
        Assert.assertEquals(vo.getState(), model.getState().name());
    }

}