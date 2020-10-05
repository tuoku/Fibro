package com.example.fibro;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
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
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    TextView text;
    ImageView icon;
    FloatingActionButton fab;
    GraphView graph;
    public static SharedPreferences prefs;
    Calendar calendar = Calendar.getInstance();
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        prefs = getSharedPreferences("days", MODE_PRIVATE);
        Days.getInstance().fetchDaysFromPrefs();

        text = findViewById(R.id.weatherText);
        icon = findViewById(R.id.weatherIcon);
        fab = findViewById(R.id.fab);
        //GPSTracker gps = new GPSTracker(this);
        refreshWeather();
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
        //prefs.edit().clear().commit();

        graph.getViewport().setYAxisBoundsManual(true);
         graph.getViewport().setXAxisBoundsManual(true);
         graph.getViewport().setMaxY(5.0);
         graph.getViewport().setMinY(1.0);

         graph.getViewport().setScrollable(true);
         graph.getViewport().setScalable(true);

       //  graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));

         graph.getGridLabelRenderer().setHumanRounding(false);
         //graph.addSeries(Graph.getInstance().getData());
           /* Graph.getInstance().getData().setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {
                Intent intent = new Intent(MainActivity.this, GraphDetailActivity.class);
                intent.putExtra("X", dataPoint.getX());
                startActivity(intent);
            }
        });*/
    }


    public void refreshWeather()
    {Weather weather = new Weather();
        weather.refresh();
        Log.d("PRESSURE AFTER THREAD", weather.getPressure());
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
    public void graphPressed(View view) {
        Intent intent = new Intent(this, GraphActivity.class);
        startActivity(intent);
    }

    public void clear(View v){
        prefs.edit().clear().commit();
       // Days.getInstance().refresh();
        PreferenceService.setIndex();
        graph.addSeries(Graph.getInstance().getData());
        graph.removeAllSeries();
        Days.getInstance().setIndex(PreferenceService.getIndex());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume(){
        graph.removeAllSeries();
        Graph.getInstance().addDaysToSeries();

        graph.addSeries(Graph.getInstance().getData());
        Log.d("MAIN", "ADDED TO SERIES");
        graph.getViewport().setMaxX(Days.getInstance().getHighestX());
        graph.getViewport().setMinX(Days.getInstance().getLowestX());
        graph.getGridLabelRenderer().setNumHorizontalLabels(Days.getInstance().getDays().size()+1);
        Log.d("LowestX", String.valueOf(Days.getInstance().getLowestX()));
        Log.d("HighestX", String.valueOf(Days.getInstance().getHighestX()));
        super.onResume();
    }



}