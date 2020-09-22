package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView tv;
TextView lat;
TextView lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);
        tv = findViewById(R.id.test);
        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);

        GPSTracker gps = new GPSTracker(this);
        lat.setText(Double.toString(gps.getLatitude()));
        lon.setText(Double.toString(gps.getLongitude()));
        Weather weather = new Weather();
        weather.getData();

        //ThreadService.enqueueWork(this, getIntent());

        //p.start();
        tv.setText(weather.getPressure());
    }


}