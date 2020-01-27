package com.example.addressbook;

import java.util.Random;

public class Location {
    private int ID;
    private String street;
    private String city;
    private String state;


    public Location() {

    }
    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {

        this.state = state;
    }


    /**
     * Constructor for location
     * @param street
     * @param city
     * @param state
     */
    public Location(String street, String city, String state) {
        Random rand = new Random();
        this.ID = rand.nextInt(99999);
        this.street = street;
        this.city = city;
        this.state = state;
    }
    @Override
    public String toString() {
        return getStreet() +"\n" + getCity() + ", " + getState();
    }
}
