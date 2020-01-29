package com.example.addressbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Contact extends AppCompatActivity {

    Button btn_call, btn_text, btn_maps, btn_website, btn_exit;

    TextView tv_name, tv_contactPhone, tv_contactAddress, tv_contactAddress2, tv_website;

    View website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact);

        Bundle incomingIntent = getIntent().getExtras();

        btn_exit = (Button)findViewById(R.id.btn_contactMainMenu);
        btn_call = (Button)findViewById(R.id.btn_call);
        btn_text = (Button)findViewById(R.id.btn_text);
        btn_maps = (Button)findViewById(R.id.btn_maps);
        btn_website = (Button)findViewById(R.id.btn_website);

        tv_name = (TextView)findViewById(R.id.tv_name);
        tv_contactPhone = (TextView)findViewById(R.id.tv_contactPhone);
        tv_contactAddress = (TextView)findViewById(R.id.tv_contactAddress);
        tv_contactAddress2 = (TextView)findViewById(R.id.tv_contactAddress2);
        tv_website = (TextView)findViewById(R.id.tv_website);

        website = (View)findViewById(R.id.website);


        if(incomingIntent != null){
            String type = incomingIntent.getString("type");
            String city = incomingIntent.getString("city");
            String state = incomingIntent.getString("state");
            String zip = incomingIntent.getString("zip");


            if(type.equals("personal")){
                tv_name.setText(incomingIntent.getString("name"));
                tv_contactPhone.setText(incomingIntent.getString("phone"));
                tv_contactAddress.setText(incomingIntent.getString("street"));
                tv_contactAddress2.setText(city + " " + state + " " + zip);
            }

            if(type.equals("business")){
                website.setVisibility(View.VISIBLE);

                tv_name.setText(incomingIntent.getString("name"));
                tv_contactPhone.setText(incomingIntent.getString("phone"));
                tv_contactAddress.setText(incomingIntent.getString("street"));
                tv_contactAddress2.setText(city + " " + state + " " + zip);
                tv_website.setText(incomingIntent.getString("website"));
            }
        }


        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + tv_contactPhone.getText().toString()));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btn_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = tv_contactPhone.getText().toString();

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("smsto:" + phone));  // This ensures only SMS apps respond

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btn_maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);

                String mapsQuery = "geo:0,0?q=" + tv_contactAddress.getText().toString() + tv_contactAddress2.getText().toString();
                Uri mapuri = Uri.parse(mapsQuery);

                intent.setData(mapuri);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btn_website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = tv_website.getText().toString();

                if(!url.startsWith("http://") || !url.startsWith("https://") )
                    url = "http://" + url;
                Uri webpage = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), MainActivity.class);
                startActivity(i);
            }
        });

    }
}
