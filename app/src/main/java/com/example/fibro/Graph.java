package com.example.fibro;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

public class Graph {
    LineGraphSeries<DataPoint> series = new LineGraphSeries<>();
    public static final Graph ourInstance = new Graph();
    private Graph(){
    series.setAnimated(true);
    }
    public static Graph getInstance(){
        return ourInstance;
    }

    public void addPoint(DataPoint d){
        ourInstance.series.appendData(d,true,100,false);
    }

    public LineGraphSeries<DataPoint> getData() {
        return series;
    }
}
