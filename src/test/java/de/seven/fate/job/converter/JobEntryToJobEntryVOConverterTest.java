package de.seven.fate.job.converter;

import de.seven.fate.address.builder.AddressBuilder;
import de.seven.fate.appointment.model.builder.AppointmentBuilder;
import de.seven.fate.converter.URLConverter;
import de.seven.fate.job.model.JobEntry;
import de.seven.fate.job.model.builder.JobEntryBuilder;
import de.seven.fate.job.vo.JobEntryVO;
import de.seven.fate.person.model.builder.PersonBuilder;
import de.seven.fate.salary.model.builder.SalaryBuilder;
import de.seven.fate.tag.model.builder.TagEntryBuilder;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Mario on 06.04.2016.
 */
public class JobEntryToJobEntryVOConverterTest {

    JobEntryToJobEntryVOConverter sut = new JobEntryToJobEntryVOConverter(new URLConverter());

    JobEntryBuilder builder = new JobEntryBuilder(new PersonBuilder(new AddressBuilder()), new AppointmentBuilder(), new TagEntryBuilder(), new SalaryBuilder());

    JobEntry model = builder.random();

    @Test
    public void convert() throws Exception {

        JobEntryVO vo = sut.convert(model);
        Assert.assertNotNull(vo);

        Assert.assertEquals(model.getId(), vo.getId());
        Assert.assertEquals(model.getDescription(), vo.getDescription());
        Assert.assertEquals(model.getTitle(), vo.getTitle());
        Assert.assertEquals(model.getFavorite(), vo.getFavorite());
        Assert.assertEquals(model.getState().name(), vo.getState());
    }
}