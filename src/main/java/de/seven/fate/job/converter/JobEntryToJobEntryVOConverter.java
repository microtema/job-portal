package de.seven.fate.job.converter;

import de.seven.fate.converter.AbstractConverter;
import de.seven.fate.converter.URLConverter;
import de.seven.fate.job.model.JobEntry;
import de.seven.fate.job.vo.JobEntryVO;

import javax.inject.Inject;

import static org.apache.commons.lang3.Validate.notNull;

/**
 * Created by Mario on 06.04.2016.
 */
public class JobEntryToJobEntryVOConverter extends AbstractConverter<JobEntryVO, JobEntry> {

    private final URLConverter urlConverter;

    @Inject
    public JobEntryToJobEntryVOConverter(URLConverter urlConverter) {
        this.urlConverter = urlConverter;
    }

    @Override
    public void update(JobEntryVO dest, JobEntry orig) {
        notNull(dest);

        if (orig == null) {
            return;
        }

        dest.setId(orig.getId());
        dest.setTitle(orig.getTitle());
        dest.setDescription(orig.getDescription());
        dest.setFavorite(orig.getFavorite());

        dest.setState(orig.getState().name());
        dest.setUrl(urlConverter.convertToDatabaseColumn(orig.getUrl()));
    }
}
