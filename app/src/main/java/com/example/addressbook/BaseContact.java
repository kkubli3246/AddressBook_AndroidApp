package com.example.addressbook;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Base Contact parent abstract class for all contacts that are to be created for an addressbook
 * @author Kekoa kubli
 *
 */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @Type(value = PersonalContact.class, name = "PersonalContact"),
        @Type(value = BusinessContact.class, name = "BusinessContact")
})
public abstract class BaseContact {
    private String name;
    private int number;
    private String phone;
    private List<Photo> photos = new ArrayList<Photo>();
    private Location location;


    public void addPhoto(Photo p) {
        photos.add(p);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public com.example.addressbook.Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }
    @Override
    public String toString() {
        return getNumber() + ". Name: " + getName() + " Phone: " + getPhone() + " \nLocation: " + this.getLocation().toString();
    }
}
