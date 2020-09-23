package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    TextView text;
    ImageView icon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);

        text = findViewById(R.id.weatherText);
        icon = findViewById(R.id.weatherIcon);
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
    }


}