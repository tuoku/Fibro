package com.example.fibro;

import android.util.Log;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for adding data to the graph.
 */
public class Graph {
    LineData lineData;
    List<String> dates = new ArrayList<>();
    List<Entry> entries = new ArrayList<Entry>();

    public static final Graph ourInstance = new Graph();
    private Graph(){

    }

    /**
     * Adds Days from days array to dataset to be shown on graph
     */
    public void init(){
        entries.clear(); //reset the dataset
        List<Day> days = Days.getInstance().getDays();
        Log.d("INIT", String.valueOf(days));
        for(Day d : days){                      //for every Day in days array
            dates.add(d.getDate().toString());  //get the date as string
            Log.d("INIT", d.getDate().toString());
            entries.add(new Entry(d.getDate().getTime(), d.getMood())); //add a new entry: X value is date in milliseconds and Y value is mood
            Log.d("INIT", "Added " + d.getDate() + " to entries");
        }
        LineDataSet dataSet = new LineDataSet(entries,"");
        dataSet.setLineWidth(4f);
        dataSet.setCircleRadius(6f);
        lineData = new LineData(dataSet);

    }

    /**
     * Returns the instance
     * @return Graph instance
     */
    public static Graph getInstance(){
        return ourInstance;
    }

    /**
     * Returns the list of dates
     * @return list of dates
     */
    public List<String> getDates(){
        return dates;
    }


}
