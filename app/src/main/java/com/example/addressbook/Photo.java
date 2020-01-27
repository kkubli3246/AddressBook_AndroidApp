package com.example.addressbook;


import java.util.Date;

public class Photo {
    private int ID;
    private Date photoDate;
    private String fileName;
    private String description;

    public Photo() {

    }
    public Photo(String fileName) {
        this.fileName = fileName;
    }
}
