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
    Day day0;
    Day day1;
    Day day2;
    Day day3;
    Day day4;
    Day day5;
    Day day6;

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
       // Type token = new TypeToken<ArrayList<Day>>(){}.getType();
       // List<Day> dayList = new ArrayList<Day>(PreferenceService.getAll().values());
        //List<Day> listi = gson.fromJson(String.valueOf(dayList.get(0)), token.getType());
        Map<String,String> resMap = PreferenceService.getAll();
        Iterator it = resMap.entrySet().iterator();
        double x = Graph.getInstance().series.getHighestValueX() + 1;
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            Log.d("WHILE",pair.getValue().toString());
            if(!(pair.getKey().equals("INDEX"))) {
                String s = pair.getValue().toString();
                Log.d("s", s);
                String ss = s.replace("[", "");
                String sss = ss.replace("]", "");
                Log.d("sss", sss);
                Day d = gson.fromJson(sss, Day.class);
                Graph.getInstance().addPoint(new DataPoint(x, d.getMood()));
                x++;
            }else break;
        }
        Log.d("looped", Graph.getInstance().series.toString());
    }

    public int getIndex(){
        return index;
    }

    public void setIndex(int i){
        this.index=i;
    }
    public Day getDay0(){return day0;}
    public Day getDay1(){return day1;}
    public Day getDay2(){return day2;}
    public Day getDay3(){return day3;}
    public Day getDay4(){return day4;}
    public Day getDay5(){return day5;}
    public Day getDay6(){return day6;}



}
