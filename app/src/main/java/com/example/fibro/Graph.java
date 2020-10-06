package com.example.fibro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.lang.reflect.Array;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    LineData lineData;
    List<String> dates = new ArrayList<>();
    List<Entry> entries = new ArrayList<Entry>();

    public static final Graph ourInstance = new Graph();
    private Graph(){
      //series.setAnimated(true);
         //series.setDrawDataPoints(true);
        //series.setSpacing(10);

    }

    public void init(){
        entries.clear();
        List<Day> days = Days.getInstance().getDays();
        Log.d("INIT", String.valueOf(days));
        for(Day d : days){
            dates.add(d.getDate().toString());
            Log.d("INIT", d.getDate().toString());
            entries.add(new Entry(d.getDate().getTime(), d.getMood()));
            Log.d("INIT", "Added " + d.getDate() + " to entires");
            LineDataSet dataSet = new LineDataSet(entries,"");
            lineData = new LineData(dataSet);

        }
    }
    public static Graph getInstance(){
        return ourInstance;
    }

   // public void addPoint(Entry e){
       // ourInstance.series.resetData(new DataPoint[]{d});
     //   ourInstance.
    //}

   // public LineGraphSeries<DataPoint> getData() {
     //   return series;
    //}

    public void addDaysToSeries(){

    }

    public List<String> getDates(){
        return dates;
    }


}
