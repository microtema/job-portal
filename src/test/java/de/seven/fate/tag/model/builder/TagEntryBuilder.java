package de.seven.fate.tag.model.builder;

import de.seven.fate.model.builder.AbstractModelBuilder;
import de.seven.fate.tag.model.TagEntry;

/**
 * Created by Mario on 05.04.2016.
 */
public class TagEntryBuilder extends AbstractModelBuilder<TagEntry> {

    @Override
    public TagEntry min() {
        TagEntry min = super.min();

        min.setId(null);

        min.setCreatedDate(null);
        min.setUpdateDate(null);
        min.setVersion(null);

        return min;
    }
}
