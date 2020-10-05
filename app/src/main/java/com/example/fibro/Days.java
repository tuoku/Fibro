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
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.*;
import com.google.gson.reflect.*;
import com.jjoe64.graphview.series.DataPoint;

public class Days {
    private ArrayList<Day> days;
    private static final Days ourInstance = new Days();
    private int index;

    Gson gson = new Gson();

    public static Days getInstance() {
        return ourInstance;
    }

    private Days() {
        days = new ArrayList<>();
        //index = Integer.parseInt(PreferenceService.getByIndex("INDEX"));
    }

    public ArrayList<Day> getDays() {
        return days;
    }


    public void fetchDaysFromPrefs() {
        Map<String, String> resMap = PreferenceService.getAll();
        Iterator it = resMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (!(pair.getKey().equals("INDEX"))) {
                String s = pair.getValue().toString();
                String ss = s.replace("[", "");
                String sss = ss.replace("]", "");
                Day d = gson.fromJson(sss, Day.class);
                days.add(d);
                Log.d("FETCHDAYS", "Added " + pair.getKey());
                //   Graph.getInstance().addPoint(new DataPoint(d.getDate().getTime(), d.getMood()));
                // L
            } else break;
        }
        Log.d("FETCHDAYS", "Added all Days to ArrayList");
        sortArray(days);
        Log.d("FETCHDAYS", "Sorted ArrayList");
    }

    private void sortArray(ArrayList<Day> arrayList) {
        if (arrayList != null) {
            Collections.sort(arrayList, new Comparator<Day>() {
                @Override
                public int compare(Day o1, Day o2) {
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        }
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int i) {
        this.index = i;
    }

    public void addDay(Day d) {
        days.add(d);
    }

    public Long getLowestX() {
        if(!(days.isEmpty())) {
            return days.get(0).getDate().getTime();
        }else return System.currentTimeMillis();
    }


    public Long getHighestX() {
        if (!(days.isEmpty())) {
            return days.get(days.size() - 1).getDate().getTime();
        }else return System.currentTimeMillis();
    }
}