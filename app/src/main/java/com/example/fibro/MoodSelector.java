package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MoodSelector extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodselector);
    }
    public void onButtonPressed(View v) {
        switch(v.getId()) {
            case R.id.awesomeButton:
                Log.i("buttonPressed", "Awesome");
                break;
            case R.id.happyButton:
                Log.i("buttonPressed", "Happy");
                break;
            case R.id.normalButton:
                Log.i("buttonPressed", "Normal");
                break;
            case R.id.sadButton:
                Log.i("buttonPressed", "Sad");
                break;
            case R.id.verySadButton:
                Log.i("buttonPressed", "Very Sad");
                break;
        }
        Intent intent = new Intent(this, MoodDetails.class);
        startActivity(intent);
    }
}