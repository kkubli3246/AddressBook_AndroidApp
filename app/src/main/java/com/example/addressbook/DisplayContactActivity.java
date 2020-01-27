package com.example.addressbook;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class DisplayContactActivity extends AppCompatActivity {


    ListView lv_contacts;
    ContactAdapter contactAdapter;

    Button btn_mainMenu;
    AddressBook ab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_all);

        ab = ((MyApplication)this.getApplication()).getAb();

        lv_contacts = findViewById(R.id.lv_contacts);
        btn_mainMenu = findViewById(R.id.btn_toMainMenu);
        contactAdapter = new ContactAdapter(DisplayContactActivity.this, ab);

        lv_contacts.setAdapter(contactAdapter);

        btn_mainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

        lv_contacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editContact(position);
            }
        });



    }
    public void editContact(int position){
        Intent i = new Intent(getApplicationContext(), NewContactForm.class);

        //get person info at position
        String type = ab.getContactList().get(position).getClass().getSimpleName();

        if(type.equals("PersonalContact")){
            PersonalContact p = (PersonalContact)ab.getContactList().get(position);

            Calendar cal = Calendar.getInstance();

            i.putExtra("name", p.getName());
            i.putExtra("phone", p.getPhone());
            i.putExtra("street", p.getLocation().getStreet());
            i.putExtra("city", p.getLocation().getCity());


            int split = p.getLocation().getState().indexOf(" ");
            String state = p.getLocation().getState().substring(0, split);
            String zip = p.getLocation().getState().substring(split);

            i.putExtra("state", state);
            i.putExtra("zip", zip);

            cal.setTime(p.getBirthday());
            int yy = cal.get(Calendar.YEAR);
            int mm = cal.get(Calendar.MONTH);
            int dd = cal.get(Calendar.DAY_OF_MONTH);


            String month = Integer.toString(mm);
            String day = Integer.toString(dd);
            String year = Integer.toString(yy);

            Log.d("kub",  month + day + year);

            if(month.length() < 2){
                month = "0" + month;
            }
            i.putExtra("mm", month);
            i.putExtra("dd", day);
            i.putExtra("yyyy", year);
            i.putExtra("type", "personal");
            i.putExtra("edited", position);




        }else if(type.equals("BusinessContact")){
            BusinessContact b = (BusinessContact)ab.getContactList().get(position);

            i.putExtra("name", b.getName());
            i.putExtra("phone", b.getPhone());
            i.putExtra("street", b.getLocation().getStreet());
            i.putExtra("city", b.getLocation().getCity());

            int split = b.getLocation().getState().indexOf(" ");
            String state = b.getLocation().getState().substring(0, split);
            String zip = b.getLocation().getState().substring(split);
            String website = b.getWebsite();

            i.putExtra("state", state);
            i.putExtra("zip", zip);
            i.putExtra("website", website);


            i.putExtra("type", "business");
            i.putExtra("edited", position);

        }




        startActivity(i);
    }
}
