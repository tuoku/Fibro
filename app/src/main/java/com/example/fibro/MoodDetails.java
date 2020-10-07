package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.sql.Date;

/**
 * Activity where user can input any extra details about their day.
 */

public class MoodDetails extends AppCompatActivity {
    EditText addDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_details);
        addDetails = findViewById(R.id.addDetails);

    }
    public void onSaveButtonPressed(View v) {
        DayCreator.getInstance().setDetails(addDetails.getText().toString()); //pass the details to DayCreator
        DayCreator.getInstance().setDate(new Date(System.currentTimeMillis())); //and the date
        DayCreator.getInstance().createDay(); // now DayCreator has all the information needed to create a new Day, so create one
        Intent intent = new Intent(this, MainActivity.class); // return to homescreen
        startActivity(intent);
    }
}