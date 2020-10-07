package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Activity for displaying the details of the selected day.
 */
public class DayDetailActivity extends AppCompatActivity {
TextView dayDateTv;
TextView dayMoodTv;
TextView dayDetailsTv;
TextView weatherPressureTv;

    // self-explanatory...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_detail);

        dayDateTv = findViewById(R.id.dayDateTv);
        dayMoodTv = findViewById(R.id.dayMoodTv);
        dayDetailsTv = findViewById(R.id.dayDetailsTv);
        weatherPressureTv = findViewById(R.id.weatherPressureTv);

        Bundle b = getIntent().getExtras(); // get the index of tapped item from GraphDetailActivity
        int i = b.getInt("EXTRA", 0);

        dayDateTv.setText(Days.getInstance().getDay(i).getDate().toString());
        dayMoodTv.setText("Vointi: " + String.valueOf(Days.getInstance().getDay(i).getMood()));
        dayDetailsTv.setText(Days.getInstance().getDay(i).getDetails());
        weatherPressureTv.setText("Ilmanpaine: " + String.valueOf(Days.getInstance().getDay(i).getPressure()));
    }
}