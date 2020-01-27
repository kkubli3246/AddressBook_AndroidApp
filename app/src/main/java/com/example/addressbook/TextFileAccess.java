package com.example.addressbook;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TextFileAccess {

    public void loadFile(File file, AddressBook ab) throws IOException {

        try {
            FileReader fr = new FileReader(file);

            List<BaseContact> contacts = new ArrayList<>();
        }
        catch (IOException e){

        }
    }

    public void saveFile(File file, AddressBook ab) throws IOException{

        try {
            FileWriter fw = new FileWriter(file);
            for(int i = 0; i < ab.getContactList().size(); i++){

                //current Index in Contact List
                BaseContact b = ab.getContactList().get(i);

                if(b.getClass().getSimpleName() == "PersonalContact"){
                    String name = b.getName();
                    String phone = b.getPhone();
                    String location = b.getLocation().getStreet() + "," + b.getLocation().getCity()+ "," + b.getLocation().getState();
                }
                else if(b.getClass().getSimpleName() == "BusinessContact   "){

                }
            }
        }
        catch (IOException e){

        }

    }
}
