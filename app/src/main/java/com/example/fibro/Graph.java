package com.example.fibro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Graph {
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
    public static final Graph ourInstance = new Graph();
    private Graph(){
      //series.setAnimated(true);
         series.setDrawDataPoints(true);
        //series.setSpacing(10);

    }
    public static Graph getInstance(){
        return ourInstance;
    }

    public void addPoint(DataPoint d){
        ourInstance.series.resetData(new DataPoint[]{d});
        ourInstance.series.appendData(d,true,20,true);
    }

    public LineGraphSeries<DataPoint> getData() {
        return series;
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addDaysToSeries(){
       List<Day> days = Days.getInstance().getDays();
       for(Day d : days){
           addPoint(new DataPoint(d.getDate(),d.getMood()));
           Log.d("addDaysToSeries", "Added " + d.getDate().getTime() + " to series");
       }
    }

}
