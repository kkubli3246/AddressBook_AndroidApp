package com.example.addressbook;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonalContact extends BaseContact {
    private Date birthday;
    private String description;
    private PersonalContact[] relatives;

    public PersonalContact() {

    }

    /**
     * creates a Personal Contact
     * @param name
     * @param phone
     * @param location
     * @param birthday
     */
    @JsonCreator
    public PersonalContact(@JsonProperty("name") String name, @JsonProperty("phone")String phone, @JsonProperty("location") Location location, @JsonProperty("birthday")Date birthday) {
        this.setName(name);
        this.setPhone(phone);
        this.setLocation(location);
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PersonalContact[] getRelatives() {
        return relatives;
    }

    public void setRelatives(PersonalContact[] relatives) {
        this.relatives = relatives;
    }



}

