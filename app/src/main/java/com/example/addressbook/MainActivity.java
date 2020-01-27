package com.example.addressbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity  {

    Button btn_addContact;
    Button btn_displayContact;
    Button btn_search;
    Button btn_exit;
    TextView tv_topText;

    AddressBook ab = new AddressBook();




    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_addContact = findViewById(R.id.btn_addContact);
        btn_displayContact = findViewById(R.id.btn_displayContacts);
        btn_search = findViewById(R.id.btn_search);
        btn_exit = findViewById(R.id.btn_exit);
        tv_topText = findViewById(R.id.tv_TopTextMain);

        FileAccessService fas = new FileAccessService(getApplicationContext());

        ab = fas.loadRecords("contacts.txt");

        ((MyApplication) this.getApplication()).setAb(ab);


        Bundle incomingMes = getIntent().getExtras();



        if (incomingMes != null) {
            String type = incomingMes.getString("type");


            if(type.equals("personal")) {
                //Creates is new Personal Contact
                Calendar cal = Calendar.getInstance();

                String name = incomingMes.getString("name");
                String phone = incomingMes.getString("phone");
                String street = incomingMes.getString("street");
                String city = incomingMes.getString("city");
                String state = incomingMes.getString("state");
                String zip = incomingMes.getString("zip");

                int month = incomingMes.getInt("month");
                int day = incomingMes.getInt("day");
                int year = incomingMes.getInt("year");
                int positionEdited = incomingMes.getInt("edited");

                cal.set(year, month, day);

                Date date = cal.getTime();

                if(positionEdited > -1){
                    ab.getContactList().remove(ab.getContactList().get(positionEdited));
                }

                //Create Locaion Object
                Location location = new Location(street, city, state + " " + zip);


                //Create Personal Contact to add to AddressBook
                PersonalContact p = new PersonalContact(name, phone, location, date);
                Toast.makeText(MainActivity.this, "Personal Contact Created! Name:" + name, Toast.LENGTH_SHORT).show();
                ab.add(p);



            }
            else if(type.equals("business")){
                String name = incomingMes.getString("name");
                String phone = incomingMes.getString("phone");
                String street = incomingMes.getString("street");
                String city = incomingMes.getString("city");
                String state = incomingMes.getString("state");
                String zip = incomingMes.getString("zip");
                String website = incomingMes.getString("website");

                //Create Locaion Object
                Location location = new Location(street, city, state + " " + zip);

                int positionEdited = incomingMes.getInt("edited");

                if(positionEdited > -1){
                    ab.getContactList().remove(ab.getContactList().get(positionEdited));
                }

                BusinessContact b = new BusinessContact(name,phone, location);
                b.setWebsite(website);
                Toast.makeText(MainActivity.this, "Business Contact Created! Name:" + name, Toast.LENGTH_SHORT).show();
                ab.add(b);
            }

            try {
                fas.saveRecords("contacts.txt",ab);
                Toast.makeText(this, "AddressBook was Updated", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), SearchContacts.class);
                startActivity(i);

            }
        });

        btn_addContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), NewContactForm.class);
                startActivity(i);
            }
        });

        btn_displayContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), DisplayContactActivity.class);
                startActivity(i);
            }
        });




    }
}
