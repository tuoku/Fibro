package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * Activity where user selects a emoji that best matches their mood.
 * The emoji represents an integer between 1 and 5.
 */

public class MoodSelector extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moodselector);
    }

    /**
     * Passes the selected mood to DayCreator and takes user to next activity
     * @param v view
     */
    public void onButtonPressed(View v) {
        int mood = 0;
        switch(v.getId()) {
            case R.id.awesomeButton:
                Log.i("buttonPressed", "Awesome");
                mood = 5;
                break;
            case R.id.happyButton:
                Log.i("buttonPressed", "Happy");
                mood = 4;
                break;
            case R.id.normalButton:
                Log.i("buttonPressed", "Normal");
                mood = 3;
                break;
            case R.id.sadButton:
                Log.i("buttonPressed", "Sad");
                mood = 2;
                break;
            case R.id.verySadButton:
                Log.i("buttonPressed", "Very Sad");
                mood = 1;
                break;
        }
        DayCreator.getInstance().setMood(mood);
        Intent intent = new Intent(this, MoodDetails.class);
        startActivity(intent);
    }
}