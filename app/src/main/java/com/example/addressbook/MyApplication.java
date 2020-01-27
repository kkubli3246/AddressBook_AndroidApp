package com.example.addressbook;

import android.app.Application;



public class MyApplication extends Application  {

    private AddressBook ab = new AddressBook();


    public com.example.addressbook.AddressBook getAb() { return ab; }

    public void setAb(com.example.addressbook.AddressBook ab) { this.ab = ab; }


}
