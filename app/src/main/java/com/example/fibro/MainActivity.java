package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    TextView text;
    ImageView icon;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);

        text = findViewById(R.id.weatherText);
        icon = findViewById(R.id.weatherIcon);
        fab = findViewById(R.id.fab);
        //GPSTracker gps = new GPSTracker(this);
        refresh();
        //ThreadService.enqueueWork(this, getIntent());
    }

    public void refresh()
    {Weather weather = new Weather();
        weather.getData();
        if(weather.isLowPressure() == true){
            text.setText("On matalapainetta!");
            icon.setImageResource(R.drawable.very_sad_emoticon);
        }else {
            text.setText("Ei matalapainetta");
            icon.setImageResource(R.drawable.awesome_emoticon);
        }
        Log.i("FIBRO",weather.getPressure());
        DayCreator.getInstance().setPressure(weather.pressure);
    }
    public void fabPressed(View view){
        Intent intent = new Intent(this, MoodSelector.class);
        startActivity(intent);
    }



}