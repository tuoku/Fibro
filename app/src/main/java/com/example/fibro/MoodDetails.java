package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MoodDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mood_details);
    }
    public void onSaveButtonPressed(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}