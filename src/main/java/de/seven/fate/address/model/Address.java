package de.seven.fate.address.model;

import de.seven.fate.dao.BaseEntity;
import de.seven.fate.listener.entity.TimestampListener;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * Created by Mario on 05.04.2016.
 */
@Entity
@EntityListeners(TimestampListener.class)
public class Address extends BaseEntity<Long> {

    private String street;
    private String streetNumber;
    private String zip;

    private String city;
    private String country;

    @NotNull
    private String email;

    @NotNull
    private String phone;

    @NotNull
    private String mobile;

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return Objects.equals(street, address.street) &&
                Objects.equals(streetNumber, address.streetNumber) &&
                Objects.equals(zip, address.zip) &&
                Objects.equals(city, address.city) &&
                Objects.equals(country, address.country) &&
                Objects.equals(email, address.email) &&
                Objects.equals(phone, address.phone) &&
                Objects.equals(mobile, address.mobile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(street, streetNumber, zip, city, country, email, phone, mobile);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("street", street)
                .append("streetNumber", streetNumber)
                .append("zip", zip)
                .append("city", city)
                .append("country", country)
                .append("email", email)
                .append("phone", phone)
                .append("mobile", mobile)
                .toString();
    }
}
