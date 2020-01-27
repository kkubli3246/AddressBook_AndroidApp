package com.example.addressbook;


import android.util.Log;

import androidx.annotation.Nullable;

import java.io.File;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonCreator;


public class AddressBook {

    private List<BaseContact> contactList = new ArrayList<BaseContact>();

    /**
     * Constructor
     */
    @JsonCreator
    public AddressBook() {

    }

    /**
     * add a contact to the AddressBook
     * @param contact
     */
    public void add(BaseContact contact) {
        contactList.add(contact);
        contact.setNumber(contactList.size());
    }

    /**
     * Removes a Contact from your addressbook
     * @param contact
     */
    public void remove(BaseContact contact) {
        contactList.remove(contact);
    }



    public List<BaseContact> getContactList() {
        return contactList;
    }

    public void setContactList(List<BaseContact> list) {
        this.contactList = list;
    }


    /**
     * outputs to the console a contact
     * @param contact
     */
    public void display(BaseContact contact) {
        if(contactList.contains(contact)) {
            System.out.print(contact.getNumber() + ".   " + contact.getClass().getSimpleName() + " " + contact.getName() + " " + contact.getPhone() + " " + "\n");
        }
        else {
            System.out.println("Contact could not be found!");
        }
    }

    /**
     * Displays All Contacts in AddressBook
     */
    public void displayAll() {
        System.out.println("\n Display All addressBook Contacts:");
        for(int i = 0; i < contactList.size(); i++) {
            BaseContact current = contactList.get(i);

            System.out.print(current.getNumber() + ".   " +current.getClass().getSimpleName() + " " + current.getName() + " " + current.getPhone() + "\n");
        }
        System.out.println("End of Contacts. \n");
    }

    /**
     *
     * Search for a name or contact number in contact list
     * @return BaseContact
     */
    public BaseContact Search(String search) {

        for(int i = 0; i < contactList.size(); i++) {
            if(contactList.get(i).getName().equals(search) || contactList.get(i).getPhone().equals(search)) {
                return contactList.get(i);
            }
        }

        return null;
    }

    /**
     * searches the addressbook for a contact based on there id
     * @param id
     * @return
     */
    public BaseContact Search(int id) {
        for(int i = 0; i < contactList.size(); i++) {
            if(contactList.get(i).getNumber() == id) {
                return contactList.get(i);
            }
        }
        return null ;
    }

    /**
     * Search Through AddressBook By Location
     * @param search
     * @return
     */
    public BaseContact SearchLocation(String search) {
        for(int i = 0; i < contactList.size(); i++) {


            Location loc = contactList.get(i).getLocation();


            if(loc.getCity().equals(search)|| loc.getStreet().equals(search)|| loc.getState().equals(search)) {

                return contactList.get(i);
            }
        }
        return null;
    }

    /**
     * Used to get all Business Contacts From the AddressBook.
     * @return
     */
    public ArrayList<BusinessContact> BusinessList(){
        ArrayList<BusinessContact> bl = new ArrayList<BusinessContact>();

        for(BaseContact contact: contactList) {
            if(contact.getClass().getSimpleName().equals("BusinessContact")) {
                bl.add((BusinessContact) contact);
            }
        }
        return bl;
    }

    /**
     * Used to get all Personal Contacts From the AddressBook
     * @return
     */
    public ArrayList<PersonalContact> PersonalList(){
        ArrayList<PersonalContact> pl = new ArrayList<PersonalContact>();

        for(BaseContact contact: contactList) {
            if(contact.getClass().getSimpleName().equals("PersonalContact")) {
                pl.add((PersonalContact) contact);
            }
        }
        return pl;
    }

    /**
     * Search for a Business Contact with a website used as an argument to search
     * @param URL
     * @return
     */
    public BusinessContact URLSearch(String URL) {
        ArrayList<BusinessContact> bl = BusinessList();
        for(BusinessContact b: bl) {
            if(b.getWebsite().equals(URL)) {
                return b;
            }
        }
        return null;
    }

    /**
     * Search through Contact List by date of Birth
     * @param dob
     * @return
     */
    public PersonalContact DOBSearch(int dob) {
        ArrayList<PersonalContact> pl = PersonalList();

        Calendar cal = Calendar.getInstance();


        for(PersonalContact p: pl) {

            cal.setTime(p.getBirthday());
            int yy = cal.get(Calendar.YEAR);
            int mm = cal.get(Calendar.MONTH);
            int dd = cal.get(Calendar.DAY_OF_MONTH);


            if(dd == dob || mm == dob || yy == dob) {
                Log.d("kub", "Search Successful");
                return p;
            }
        }
        return null;
    }

}

