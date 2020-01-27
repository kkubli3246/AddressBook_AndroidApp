package com.example.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SearchContacts extends AppCompatActivity {

    Button btn_exitToMenu, btn_searchProp;
    EditText et_input;

    TextView tv_name, tv_phone, tv_type;
    ImageView iv_photo;
    Spinner spinner;
    View include_contact;

    AddressBook ab = new AddressBook();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_contact);

        FileAccessService fas = new FileAccessService(getApplicationContext());

        ab = fas.loadRecords("contacts.txt");

        include_contact = findViewById(R.id.singleContact);
        btn_exitToMenu = findViewById(R.id.btn_toMainMenu);
        btn_searchProp = findViewById(R.id.btn_searchProp);
        et_input = findViewById(R.id.et_propName);
        spinner = findViewById(R.id.spin);

        //Contact Views
        tv_name = findViewById(R.id.tv_contactName);
        tv_phone = findViewById(R.id.tv_contactPhone);
        tv_type = findViewById(R.id.tv_typeOfContact);
        iv_photo = findViewById(R.id.iv_photo);

        btn_searchProp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = spinner.getSelectedItem().toString().toLowerCase();
                String input = et_input.getText().toString();

                if(type.equals("name") || type.equals("phone")){
                    BaseContact contact;
                    if(ab.Search(input) != null){
                       contact = ab.Search(input);
                       include_contact.setVisibility(View.VISIBLE);
                       tv_name.setText(contact.getName());
                       tv_phone.setText(contact.getPhone());
                       tv_type.setText(contact.getClass().getSimpleName());


                    }else{
                        Toast.makeText(SearchContacts.this, "No contacts match your search: " + input, Toast.LENGTH_SHORT).show();
                    }
                }else if (type.equals("street") || type.equals("city") || type.equals("state")){
                    BaseContact contact;
                    if(ab.SearchLocation(input) != null){
                        contact = ab.SearchLocation(input);
                        include_contact.setVisibility(View.VISIBLE);
                        tv_name.setText(contact.getName());
                        tv_phone.setText(contact.getPhone());
                        tv_type.setText(contact.getClass().getSimpleName());


                    }else{
                        Toast.makeText(SearchContacts.this, "No contacts match your search: " + input, Toast.LENGTH_SHORT).show();
                    }

                }else if (type.equals("dob - day")){
                    PersonalContact contact;

                    if(ab.DOBSearch(Integer.parseInt(input)) != null){
                        Log.d("kub", type + " " + input);
                        contact = ab.DOBSearch(Integer.parseInt(input));
                        include_contact.setVisibility(View.VISIBLE);
                        tv_name.setText(contact.getName());
                        tv_phone.setText(contact.getPhone());
                        tv_type.setText(contact.getClass().getSimpleName());

                    }else{
                        Toast.makeText(SearchContacts.this, "No contacts match your search: " + input, Toast.LENGTH_SHORT).show();
                    }

                }else if (type.equals("dob - month")){
                    PersonalContact contact;

                    if(ab.DOBSearch(Integer.parseInt(input)) != null){
                        Log.d("kub", type + " " + input);
                        contact = ab.DOBSearch(Integer.parseInt(input));
                        include_contact.setVisibility(View.VISIBLE);
                        tv_name.setText(contact.getName());
                        tv_phone.setText(contact.getPhone());
                        tv_type.setText(contact.getClass().getSimpleName());

                    }else{
                        Toast.makeText(SearchContacts.this, "No contacts match your search: " + input, Toast.LENGTH_SHORT).show();
                    }

                }else if (type.equals("dob - year")){
                    PersonalContact contact;

                    if(ab.DOBSearch(Integer.parseInt(input)) != null){
                        Log.d("kub", type + " " + input);
                        contact = ab.DOBSearch(Integer.parseInt(input));
                        include_contact.setVisibility(View.VISIBLE);
                        tv_name.setText(contact.getName());
                        tv_phone.setText(contact.getPhone());
                        tv_type.setText(contact.getClass().getSimpleName());

                    }else{
                        Toast.makeText(SearchContacts.this, "No contacts match your search: " + input, Toast.LENGTH_SHORT).show();
                    }
                }else if (type.equals("website url")){
                    BusinessContact contact;

                    if(ab.URLSearch(input) != null){
                        Log.d("kub", type + " " + input);
                        contact = ab.URLSearch(input);
                        include_contact.setVisibility(View.VISIBLE);
                        tv_name.setText(contact.getName());
                        tv_phone.setText(contact.getPhone());
                        tv_type.setText(contact.getClass().getSimpleName());

                    }else{
                        Toast.makeText(SearchContacts.this, "No contacts match your search: " + input, Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });

        btn_exitToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}
