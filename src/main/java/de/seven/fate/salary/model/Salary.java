package de.seven.fate.salary.model;

import de.seven.fate.dao.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Created by Mario on 05.04.2016.
 */
@Entity
public class Salary extends BaseEntity<Long> {

    @NotNull
    private BigDecimal min;

    @NotNull
    private BigDecimal max;

    public BigDecimal getMin() {
        return min;
    }

    public void setMin(BigDecimal min) {
        this.min = min;
    }

    public BigDecimal getMax() {
        return max;
    }

    public void setMax(BigDecimal max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Salary salary = (Salary) o;
        return Objects.equals(min, salary.min) &&
                Objects.equals(max, salary.max);
    }

    @Override
    public int hashCode() {
        return Objects.hash(min, max);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("min", min)
                .append("max", max)
                .toString();
    }
}
