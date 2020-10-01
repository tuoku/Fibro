package com.example.fibro;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.*;
import com.google.gson.reflect.*;
import com.jjoe64.graphview.series.DataPoint;

public class Days {
    private List<Day> days;
    private static final Days ourInstance = new Days();
    private int index;

    Gson gson = new Gson();

    public static Days getInstance(){
        return ourInstance;
    }

    private Days(){
        days = new ArrayList<>();
        //index = Integer.parseInt(PreferenceService.getByIndex("INDEX"));
    }

    public List<Day> getDays(){
        return days;
    }

    public void refresh(){
        Map<String,String> resMap = PreferenceService.getAll();
        Iterator it = resMap.entrySet().iterator();
        double x = Graph.getInstance().series.getHighestValueX() + 1;
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            if(!(pair.getKey().equals("INDEX"))) {
                String s = pair.getValue().toString();
                String ss = s.replace("[", "");
                String sss = ss.replace("]", "");
                Day d = gson.fromJson(sss, Day.class);
                Graph.getInstance().addPoint(new DataPoint(x, d.getMood()));
                x++;
            }else break;
        }
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int i){
        this.index=i;
    }
}
