package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;

import java.sql.Date;

public class MoodDetails extends AppCompatActivity {
    EditText addDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_details);
        addDetails = findViewById(R.id.addDetails);

    }
    public void onSaveButtonPressed(View v) {
        DayCreator.getInstance().setDetails(addDetails.getText().toString());
        DayCreator.getInstance().setDate(new Date(System.currentTimeMillis()));
        DayCreator.getInstance().createDay();
        Gson gson = new Gson();
        String jsonDays = gson.toJson(Days.getInstance().getDays());
        SharedPreferences prefPut = getSharedPreferences("days", Activity.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = prefPut.edit();
        prefEditor.putString(String.valueOf(new Date(System.currentTimeMillis())),jsonDays);
        prefEditor.apply();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}