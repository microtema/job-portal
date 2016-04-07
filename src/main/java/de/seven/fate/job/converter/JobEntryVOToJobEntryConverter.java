package de.seven.fate.job.converter;

import de.seven.fate.converter.AbstractConverter;
import de.seven.fate.converter.EnumConverter;
import de.seven.fate.converter.URLConverter;
import de.seven.fate.job.model.JobEntry;
import de.seven.fate.job.vo.JobEntryVO;
import de.seven.fate.salary.model.JobEntryState;

import javax.inject.Inject;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by Mario on 06.04.2016.
 */
public class JobEntryVOToJobEntryConverter extends AbstractConverter<JobEntry, JobEntryVO> {

    private final URLConverter urlConverter;
    private final EnumConverter<JobEntryState> enumConverter;

    @Inject
    public JobEntryVOToJobEntryConverter(URLConverter urlConverter, EnumConverter<JobEntryState> enumConverter) {
        this.urlConverter = urlConverter;
        this.enumConverter = enumConverter;
    }

    @Override
    public void update(JobEntry dest, JobEntryVO orig) {
        notNull(dest);

        if (orig == null) {
            return;
        }

        dest.setId(orig.getId());
        dest.setTitle(orig.getTitle());
        dest.setDescription(orig.getDescription());
        dest.setFavorite(orig.getFavorite());

        dest.setState(enumConverter.convertToEntityAttribute(orig.getState(), JobEntryState.class));
        dest.setUrl(urlConverter.convertToEntityAttribute(orig.getUrl()));
    }
}
