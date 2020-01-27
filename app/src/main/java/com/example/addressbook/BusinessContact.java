package com.example.addressbook;

import com.fasterxml.jackson.annotation.*;
public class BusinessContact extends BaseContact {

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    private String website;
    private String hours;

    public BusinessContact() {

    }

    /**
     * creates a business contact
     * @param name
     * @param phone
     * @param location
     */
    @JsonCreator
    public BusinessContact(@JsonProperty("name") String name, @JsonProperty("phone") String phone,@JsonProperty("location") Location location) {
        this.setName(name);
        this.setPhone(phone);
        this.setLocation(location);
    }
}
