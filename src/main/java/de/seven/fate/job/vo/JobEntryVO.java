package de.seven.fate.job.vo;

import de.seven.fate.converter.URLConverter;
import de.seven.fate.person.model.Person;
import de.seven.fate.salary.model.JobEntryState;
import de.seven.fate.salary.model.Salary;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import javax.persistence.Convert;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.net.URL;
import java.util.Objects;

/**
 * Created by Mario on 06.04.2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class JobEntryVO implements Serializable {

    private Long id;
    private String title;
    private String description;

    private String url;
    private String state;

    private int favorite;

    //  private Salary salary;
    //  private Person humanResource;
    //  private Person jobHunter;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobEntryVO that = (JobEntryVO) o;
        return favorite == that.favorite &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description) &&
                Objects.equals(url, that.url) &&
                Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, url, state, favorite);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("title", title)
                .append("description", description)
                .append("url", url)
                .append("state", state)
                .append("favorite", favorite)
                .toString();
    }
}
