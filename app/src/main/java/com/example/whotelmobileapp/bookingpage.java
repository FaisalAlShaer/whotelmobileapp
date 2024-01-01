package com.example.whotelmobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class bookingpage extends AppCompatActivity {

    EditText phone, NoOfDays, name;
    Button calcPrice, BookBtn;
    TextView displayPrice, returnHomePage;
    RadioGroup RgRoomType;
    RadioButton KingRoomRb, regularRoomRb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookingpage);

        name = findViewById(R.id.NameBookingED);
        phone = findViewById(R.id.PhoneBookingED);
        NoOfDays = findViewById(R.id.NoOfDaysED);
        calcPrice = findViewById(R.id.calculatePricebtn);
        BookBtn = findViewById(R.id.Bookbtn);
        displayPrice = findViewById(R.id.displayPrice);
        returnHomePage = findViewById(R.id.returnHomepage);
        RgRoomType = findViewById(R.id.RgRoomType);
        KingRoomRb = findViewById(R.id.KingRoomRB);
        regularRoomRb = findViewById(R.id.RegularRoomRB);


        returnHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(bookingpage.this, homepage.class);
                startActivity(intent);
            }
        });
        calcPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateTotalPrice();
            }
        });

        BookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookRoom();
            }
        });
    }

    private void calculateTotalPrice() {
        int numberOfDays = Integer.parseInt(NoOfDays.getText().toString());
        String roomType = getSelectedRoomType();
        double totalPrice = pricecalculator.calculateTotalPrice(roomType, numberOfDays);

        displayPrice.setText("Total Price: " + totalPrice + " OMR");
    }

    private String getSelectedRoomType() {
        int selectedRadioButtonId = RgRoomType.getCheckedRadioButtonId();

        if (selectedRadioButtonId == KingRoomRb.getId()) {
            return "King";
        } else if (selectedRadioButtonId == regularRoomRb.getId()) {
            return "regular";
        }

        // Default to regular room type if no option is selected
        return "regular";
    }

    private void bookRoom() {
        int numberOfDays = Integer.parseInt(NoOfDays.getText().toString());
        String roomType = getSelectedRoomType();
        double totalPrice = pricecalculator.calculateTotalPrice(roomType, numberOfDays);

        // Get the user's name and phone number from the EditText fields
        String userName = name.getText().toString();
        String userPhone = phone.getText().toString();

        DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference("booking");

        String bookingId = bookingRef.push().getKey();

        Map<String, Object> bookingData = new HashMap<>();
        bookingData.put("NoOfDays", numberOfDays);
        bookingData.put("RoomType", roomType);
        bookingData.put("totalPrice", totalPrice);
        bookingData.put("name", userName);
        bookingData.put("phone", userPhone);



        bookingRef.child(bookingId).setValue(bookingData);

        Toast.makeText(bookingpage.this, "Booking Successful", Toast.LENGTH_SHORT).show();

    }

}