package com.example.fibro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView text;
    ImageView icon;
    FloatingActionButton fab;
    public static SharedPreferences prefs;
    public static Context context; //make a global variable so other classes can use MainActivitys Context
    LineChart graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getApplicationContext();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build(); // Thread not working so temp fix to allow reaching API
        StrictMode.setThreadPolicy(policy);
        prefs = getSharedPreferences("days", MODE_PRIVATE);
        //prefs.edit().clear().commit();
        Days.getInstance().fetchDaysFromPrefs();    //Get Days from SharedPreferences

        text = findViewById(R.id.weatherText);
        icon = findViewById(R.id.weatherIcon);
        fab = findViewById(R.id.fab);
        graph = findViewById(R.id.graph);

        refreshWeather(); //Updates the weather widget
    }

    /**
     * Method to refresh the weather widget
     */
    public void refreshWeather() {
        Weather weather = new Weather();
        weather.refresh(); //get the weather data from API
        Log.d("PRESSURE AFTER THREAD", weather.getPressure());
        Log.d("pressure length", String.valueOf(weather.getPressure().length()));
        if(weather.getPressure().length() == 3) { //If API couldn't be reached in reasonable time
            text.setText("Cannot reach API");
            icon.setImageResource(R.drawable.very_sad_emoticon);
            Log.d("equals 0", "eq0");
        }else {
            if (weather.isLowPressure() == true) { //in this app low pressure is defined as atmospheric pressure below 1,013 hPa
                text.setText("On matalapainetta!");
                icon.setImageResource(R.drawable.very_sad_emoticon);
            } else {
                text.setText("Ei matalapainetta");
                icon.setImageResource(R.drawable.awesome_emoticon);
            }
        }
        Log.i("FIBRO",weather.getPressure());
        DayCreator.getInstance().setPressure(weather.pressure); // pass the pressure information to DayCreator, will be used as parameter once user logs a new day
    }

    /**
     * Once the FloatingActionButton is pressed,
     * take the user to the next activity to input data,
     * or if user has already logged info for today show a
     * AlertDialog asking user if (s)he wishes to overwrite the data
     * @param view view
     */
    public void fabPressed(View view){
        boolean alreadyLoggedToday = false;
        for(Day d : Days.getInstance().getDays()){ //check if any Day in the days array matches the current date
            if(d.date.toString().equals(new java.sql.Date(System.currentTimeMillis()).toString())){
                alreadyLoggedToday = true;
            }
        } Log.d("AlreadyLogged", String.valueOf(alreadyLoggedToday));
        if(alreadyLoggedToday == true){ //if found existing date
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this); //create the AlertDialog
            builder.setMessage("Haluatko korvata tiedot uusilla?")
                    .setTitle("Olet jo kirjannut tämän päivän tiedot");
            builder.setPositiveButton("Kyllä", new DialogInterface.OnClickListener() { //if user wishes to overwrite, go to mood selector
                public void onClick(DialogInterface dialog, int id) {
                    Intent intent = new Intent(MainActivity.context, MoodSelector.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("En", new DialogInterface.OnClickListener() { // if not, do nothing
                public void onClick(DialogInterface dialog, int id) {

                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();

        } else { //if user had not logged today yet go to mood selector
            Intent intent = new Intent(this, MoodSelector.class);
            startActivity(intent);
        }

    }

    /**
     * Takes user to GraphDetailActivity once graph is tapped
     * @param view view
     */
    public void graphPressed(View view) {
        Intent intent = new Intent(this, GraphDetailActivity.class);
        startActivity(intent);
    }

    /**
     * Clear data from SharedPreferences
     * @param v view
     */
    public void clear(View v){
        prefs.edit().clear().commit();
    }

    @Override
    protected void onResume(){
        XAxis x = graph.getXAxis();
        x.setValueFormatter(new ValueFormatter() {
            @Override                                   //Override ValueFormatter to show our dates as dates and not milliseconds
            public String getFormattedValue(float value) {
                SimpleDateFormat sdp = new SimpleDateFormat("dd-MM");
                return sdp.format(new Date(new Float(value).longValue()));
            }
        });

// X-axis styling

       // x.setGranularity(1f);
        //x.setGranularityEnabled(true);
        x.setCenterAxisLabels(false);
        x.setXOffset(-10);
       // x.setLabelCount(Graph.getInstance().entries.size()+2,true);
        x.setDrawGridLines(false);
        //x.setAxisMaximum(Days.getInstance().getHighestX());
        //x.setAxisMinimum(Days.getInstance().getLowestX());
        x.setPosition(XAxis.XAxisPosition.BOTTOM);

// Y-Axis Styling

        YAxis y = graph.getAxisLeft();
        y.setLabelCount(5);
        y.setDrawGridLinesBehindData(false);

        graph.getAxisRight().setEnabled(false);
        Graph.getInstance().init(); //adds days from Days to dataset
        graph.setDrawGridBackground(false);
        graph.setData(Graph.getInstance().lineData);// applies the dataset to the graph
        graph.invalidate(); //despite the name, actually refreshes the graph

        super.onResume();
    }



}