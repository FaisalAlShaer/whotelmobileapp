package com.example.whotelmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class room1 extends AppCompatActivity {

    Button nr , hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);

        nr = findViewById(R.id.r1btn1);
        hp = findViewById(R.id.r1btn2);

        nr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(room1.this, room2.class);
                startActivity(intent);
            }
        });
        hp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(room1.this, homepage.class);
                startActivity(intent);
            }
        });
    }
}