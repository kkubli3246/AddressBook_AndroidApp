package com.example.addressbook;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class NewContactForm extends AppCompatActivity {

    RadioButton rbtn_Personal, rbtn_Business;
    RadioGroup radioGroup;
    View includePersonal;
    View includeBusiness;

    Button btn_mainMenu, btn_submit, btn_delete, btn_contact;

    AddressBook ab;

    int positionToEdit = -1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_contact);

        ab = ((MyApplication)this.getApplication()).getAb();

        rbtn_Personal = findViewById(R.id.rbtn_Personal);
        rbtn_Business = findViewById(R.id.rbtn_Business);
        radioGroup = findViewById(R.id.rg_radioGroup);
        includePersonal = findViewById(R.id.include_personal);
        includeBusiness = findViewById(R.id.include_business);
        btn_mainMenu = findViewById(R.id.btn_toMainMenu);
        btn_submit = findViewById(R.id.btn_submit);
        btn_delete = findViewById(R.id.btn_delete);
        btn_contact = findViewById(R.id.btn_Contact);

        Bundle incomingIntent = getIntent().getExtras();

        /* incoming Intent from when editing existing contact */
        if(incomingIntent != null){
            String type = incomingIntent.getString("type");

            positionToEdit = incomingIntent.getInt("edited");

            btn_delete.setVisibility(View.VISIBLE);
            btn_contact.setVisibility(View.VISIBLE);

            if(type.equals("personal")){
                radioGroup.check(rbtn_Personal.getId());
                includePersonal.setVisibility(View.VISIBLE);

                EditText et_nameP = findViewById(R.id.et_nameP);
                EditText et_phoneP = findViewById(R.id.et_phoneP);
                EditText et_month = findViewById(R.id.et_mm);
                EditText et_day = findViewById(R.id.et_dd);
                EditText et_year = findViewById(R.id.et_yyyy);
                EditText et_street = findViewById(R.id.et_streetP);
                EditText et_city = findViewById(R.id.et_cityP);
                EditText et_state = findViewById(R.id.et_stateP);
                EditText et_zip = findViewById(R.id.et_zipP);

                et_nameP.setText(incomingIntent.getString("name"));
                et_phoneP.setText(incomingIntent.getString("phone"));
                et_street.setText(incomingIntent.getString("street"));
                et_city.setText(incomingIntent.getString("city"));
                et_state.setText(incomingIntent.getString("state"));
                et_zip.setText(incomingIntent.getString("zip"));
                et_month.setText(incomingIntent.getString("mm"));
                et_day.setText(incomingIntent.getString("dd"));
                et_year.setText(incomingIntent.getString("yyyy"));

            }
            else if(type.equals("business")){
                radioGroup.check(rbtn_Business.getId());
                includeBusiness.setVisibility(View.VISIBLE);

                EditText et_name = findViewById(R.id.et_nameB);
                EditText et_phone = findViewById(R.id.et_phoneB);

                EditText et_street = findViewById(R.id.et_streetB);
                EditText et_city = findViewById(R.id.et_cityB);
                EditText et_state = findViewById(R.id.et_stateB);
                EditText et_zip = findViewById(R.id.et_zipB);
                EditText et_website = findViewById(R.id.et_website);

                et_name.setText(incomingIntent.getString("name"));
                et_phone.setText(incomingIntent.getString("phone"));
                et_street.setText(incomingIntent.getString("street"));
                et_city.setText(incomingIntent.getString("city"));
                et_state.setText(incomingIntent.getString("state"));
                et_zip.setText(incomingIntent.getString("zip"));
                et_website.setText(incomingIntent.getString("website"));


            }
        }


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == rbtn_Personal.getId()){
                    includePersonal.setVisibility(View.VISIBLE);
                    includeBusiness.setVisibility(View.INVISIBLE);
                }
                else if(checkedId == rbtn_Business.getId()){
                    includePersonal.setVisibility(View.INVISIBLE);
                    includeBusiness.setVisibility(View.VISIBLE);
                }
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);



                if(rbtn_Personal.isChecked()){
                    EditText et_nameP = findViewById(R.id.et_nameP);
                    EditText et_phoneP = findViewById(R.id.et_phoneP);
                    EditText et_month = findViewById(R.id.et_mm);
                    EditText et_day = findViewById(R.id.et_dd);
                    EditText et_year = findViewById(R.id.et_yyyy);
                    EditText et_street = findViewById(R.id.et_streetP);
                    EditText et_city = findViewById(R.id.et_cityP);
                    EditText et_state = findViewById(R.id.et_stateP);
                    EditText et_zip = findViewById(R.id.et_zipP);

                    //Collect Input Data to create a New Contact
                    String name = et_nameP.getText().toString();
                    String phone = et_phoneP.getText().toString();
                    String street = et_street.getText().toString();
                    String city = et_city.getText().toString();
                    String state = et_state.getText().toString();
                    String zip = et_zip.getText().toString();

                    int month = Integer.parseInt(et_month.getText().toString());
                    int day = Integer.parseInt(et_day.getText().toString());
                    int year = Integer.parseInt(et_year.getText().toString());

                    i.putExtra("name", name);
                    i.putExtra("phone", phone);
                    i.putExtra("street", street);
                    i.putExtra("city", city);
                    i.putExtra("state", state);
                    i.putExtra("zip", zip);
                    i.putExtra("month", month);
                    i.putExtra("day", day);
                    i.putExtra("year", year);
                    i.putExtra("edited", positionToEdit);
                    i.putExtra("type", "personal");

                    startActivity(i);
                }

                if(rbtn_Business.isChecked()){
                    EditText et_name = findViewById(R.id.et_nameB);
                    EditText et_phone = findViewById(R.id.et_phoneB);
                    EditText et_street = findViewById(R.id.et_streetB);
                    EditText et_city = findViewById(R.id.et_cityB);
                    EditText et_state = findViewById(R.id.et_stateB);
                    EditText et_zip = findViewById(R.id.et_zipB);
                    EditText et_website = findViewById(R.id.et_website);

                    String name = et_name.getText().toString();
                    String phone = et_phone.getText().toString();
                    String street = et_street.getText().toString();
                    String city = et_city.getText().toString();
                    String state = et_state.getText().toString();
                    String zip = et_zip.getText().toString();
                    String website = et_website.getText().toString();

                    i.putExtra("name", name);
                    i.putExtra("phone", phone);
                    i.putExtra("street", street);
                    i.putExtra("city", city);
                    i.putExtra("state", state);
                    i.putExtra("zip", zip);
                    i.putExtra("website", website);
                    i.putExtra("edited", positionToEdit);
                    i.putExtra("type", "business");

                    startActivity(i);
                }
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                ab.getContactList().remove(ab.getContactList().get(positionToEdit));

                FileAccessService fas = new FileAccessService(v.getContext());

                try {
                    fas.saveRecords("contacts.txt",ab);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivity(i);
            }

        });

        btn_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), Contact.class);
                if(rbtn_Personal.isChecked()){
                    EditText et_nameP = findViewById(R.id.et_nameP);
                    EditText et_phoneP = findViewById(R.id.et_phoneP);
                    EditText et_month = findViewById(R.id.et_mm);
                    EditText et_day = findViewById(R.id.et_dd);
                    EditText et_year = findViewById(R.id.et_yyyy);
                    EditText et_street = findViewById(R.id.et_streetP);
                    EditText et_city = findViewById(R.id.et_cityP);
                    EditText et_state = findViewById(R.id.et_stateP);
                    EditText et_zip = findViewById(R.id.et_zipP);

                    //Collect Input Data to create a New Contact
                    String name = et_nameP.getText().toString();
                    String phone = et_phoneP.getText().toString();
                    String street = et_street.getText().toString();
                    String city = et_city.getText().toString();
                    String state = et_state.getText().toString();
                    String zip = et_zip.getText().toString();

                    int month = Integer.parseInt(et_month.getText().toString());
                    int day = Integer.parseInt(et_day.getText().toString());
                    int year = Integer.parseInt(et_year.getText().toString());

                    i.putExtra("name", name);
                    i.putExtra("phone", phone);
                    i.putExtra("street", street);
                    i.putExtra("city", city);
                    i.putExtra("state", state);
                    i.putExtra("zip", zip);
                    i.putExtra("month", month);
                    i.putExtra("day", day);
                    i.putExtra("year", year);
                    i.putExtra("type", "personal");

                    startActivity(i);
                }

                if(rbtn_Business.isChecked()){
                    EditText et_name = findViewById(R.id.et_nameB);
                    EditText et_phone = findViewById(R.id.et_phoneB);
                    EditText et_street = findViewById(R.id.et_streetB);
                    EditText et_city = findViewById(R.id.et_cityB);
                    EditText et_state = findViewById(R.id.et_stateB);
                    EditText et_zip = findViewById(R.id.et_zipB);
                    EditText et_website = findViewById(R.id.et_website);

                    String name = et_name.getText().toString();
                    String phone = et_phone.getText().toString();
                    String street = et_street.getText().toString();
                    String city = et_city.getText().toString();
                    String state = et_state.getText().toString();
                    String zip = et_zip.getText().toString();
                    String website = et_website.getText().toString();

                    i.putExtra("name", name);
                    i.putExtra("phone", phone);
                    i.putExtra("street", street);
                    i.putExtra("city", city);
                    i.putExtra("state", state);
                    i.putExtra("zip", zip);
                    i.putExtra("website", website);
                    i.putExtra("type", "business");

                    startActivity(i);
                }

                startActivity(i);
            }
        });




    }
}
