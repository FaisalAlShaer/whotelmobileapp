package com.example.whotelmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class homepage extends AppCompatActivity {

    Button vr,bp, logoutbtn, toMybookingpage;
    TextView cu,abus;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        auth = FirebaseAuth.getInstance();

        logoutbtn = findViewById(R.id.logoutbtn);
        vr = findViewById(R.id.hpbutton1);
        bp = findViewById(R.id.hpbutton2);
        abus = findViewById(R.id.hptv1);
        cu = findViewById(R.id.hptv2);
        toMybookingpage = findViewById(R.id.toMyBooking);

        //logout button
        logoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        vr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(homepage.this, room1.class);
                startActivity(intent);

            }
        });
        bp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(homepage.this, bookingpage.class);
                startActivity(intent);

            }
        });
        abus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(homepage.this, aboutus.class);
                startActivity(intent);

            }
        });
        cu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(homepage.this, contactus.class);
                startActivity(intent);

            }
        });
        toMybookingpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(homepage.this, showmybooking.class);
                startActivity(intent);
            }
        });

    }
}