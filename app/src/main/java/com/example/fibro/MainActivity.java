package com.example.fibro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jjoe64.graphview.DefaultLabelFormatter;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    TextView text;
    ImageView icon;
    FloatingActionButton fab;
    GraphView graph;
    public static SharedPreferences prefs;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
       StrictMode.setThreadPolicy(policy);
       prefs = getSharedPreferences("days", MODE_PRIVATE);
       Days.getInstance().refresh();
       PreferenceService.setIndex();
       Days.getInstance().setIndex(PreferenceService.getIndex());


        text = findViewById(R.id.weatherText);
        icon = findViewById(R.id.weatherIcon);
        fab = findViewById(R.id.fab);
        //GPSTracker gps = new GPSTracker(this);
        refresh();
        //ThreadService.enqueueWork(this, getIntent());
         graph = findViewById(R.id.graph);
        graph.getGridLabelRenderer().setLabelFormatter(new DefaultLabelFormatter() {
            @Override
            public String formatLabel(double value, boolean isValueX) {
                if (isValueX) {
                    Format formatter = new SimpleDateFormat("dd-MM");
                    return formatter.format(value);
                }
                return super.formatLabel(value, isValueX);
            }
        });
        prefs.edit().clear().commit();

        graph.getViewport().setYAxisBoundsManual(true);
         graph.getViewport().setXAxisBoundsManual(true);
         graph.getViewport().setMaxY(5.0);
         graph.getViewport().setMinY(1.0);
         graph.getViewport().setMaxX(Graph.getInstance().series.getLowestValueX() + (1000*60*60*24*7));
         graph.getViewport().setMinX(Graph.getInstance().series.getLowestValueX());
         graph.getViewport().setScrollable(true);
        // graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
         graph.getGridLabelRenderer().setNumHorizontalLabels(7);
         graph.getGridLabelRenderer().setHumanRounding(true);
         graph.addSeries(Graph.getInstance().getData());

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

    public void clear(View v){
        prefs.edit().clear().commit();
        Days.getInstance().refresh();
        PreferenceService.setIndex();
        graph.addSeries(Graph.getInstance().getData());
        graph.removeAllSeries();
        Days.getInstance().setIndex(PreferenceService.getIndex());
    }

    @Override
    protected void onResume(){
        Days.getInstance().refresh();
        graph.addSeries(Graph.getInstance().getData());
        super.onResume();
    }



}