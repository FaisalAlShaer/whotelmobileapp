package com.example.whotelmobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showmybooking extends AppCompatActivity {

    EditText BookingName, PhoneNumber;
    TextView totalPricegiven;
    Button viewBookingbtn, DeleteBtn, SaveInfoBtn, goToHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showmybooking);

        BookingName = findViewById(R.id.editTextBookingName);
        PhoneNumber = findViewById(R.id.editTextPhoneNumber);


        totalPricegiven = findViewById(R.id.tvViewPrice);

        viewBookingbtn = findViewById(R.id.DisplayBookingbtn);
        DeleteBtn = findViewById(R.id.DeleteBookingBtn);
        SaveInfoBtn = findViewById(R.id.SaveChangesBtn);
        goToHome = findViewById(R.id.goToHomePage);

        viewBookingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve booking data from Firebase
                DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference("booking");
                bookingRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            // Assuming there is only one booking
                            DataSnapshot firstBooking = dataSnapshot.getChildren().iterator().next();

                            // Retrieve booking data
                            String bookingName = firstBooking.child("name").getValue(String.class);
                            String phoneNumber = firstBooking.child("phone").getValue(String.class);

                            double totalPrice = firstBooking.child("totalPrice").getValue(Double.class);

                            // Display data in EditText fields and TextView
                            BookingName.setText(bookingName);
                            PhoneNumber.setText(phoneNumber);

                            totalPricegiven.setText("PRICE: " + totalPrice + " OMR");
                        } else {
                            Toast.makeText(showmybooking.this, "Booking not found", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Handle error
                    }
                });
            }
        });
        DeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Delete the booking from Firebase
                DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference("booking");
                bookingRef.removeValue();

                // Inform the user
                Toast.makeText(showmybooking.this, "Booking deleted successfully", Toast.LENGTH_LONG).show();
            }
        });
        SaveInfoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Update booking information in Firebase
                DatabaseReference bookingRef = FirebaseDatabase.getInstance().getReference("booking");
                bookingRef.child("name").setValue(BookingName.getText().toString().trim());
                bookingRef.child("phone").setValue(PhoneNumber.getText().toString().trim());


                // Inform the user
                Toast.makeText(showmybooking.this, "Changes saved", Toast.LENGTH_LONG).show();
            }
        });
        goToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate back to the home page
                finish();
            }
        });


    }
}
