package de.seven.fate.tag.model;

import de.seven.fate.dao.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import java.util.Objects;

/**
 * Created by Mario on 05.04.2016.
 */
@Entity
public class TagEntry extends BaseEntity<Long> {

    private String label;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntry tagEntry = (TagEntry) o;
        return Objects.equals(label, tagEntry.label);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("label", label)
                .toString();
    }
}
