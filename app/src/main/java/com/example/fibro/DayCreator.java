package com.example.fibro;

import android.util.Log;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.sql.Date;

public class DayCreator {
    private Date date;
    private int mood;
    private String details;
    private double pressure;

    private static final DayCreator ourInstance = new DayCreator();

    public static DayCreator getInstance(){
        return ourInstance;
    }

    private DayCreator(){

    }
    public void setDate(Date date){this.date = date;}
    public Date getDate(){return this.date;}
    public void setMood(int mood){this.mood = mood;}
    public int getMood(){return this.mood;}
    public void setDetails(String details){this.details = details;}
    public String getDetails(){return this.details;}
    public void setPressure(double pressure){this.pressure = pressure;}
    public double getPressure(){return this.pressure;}

    public void createDay() {
        Gson gson = new Gson();
        Day d = new Day(date, mood, details, pressure);
        int index = 0;
        Boolean loggedAlready = false;
        for (Day dd : Days.getInstance().getDays()) {
            if (dd.getDate().toString().equals(d.getDate().toString())) {
                loggedAlready = true;
            }
        }
        if (!loggedAlready) {
            String jsonDays = gson.toJson(d);
            String key = d.getDate().toString();
            Log.d("save",jsonDays);
            Days.getInstance().addDay(d);
            PreferenceService.saveData(key, jsonDays);
        } else {
            for (Day day : Days.getInstance().getDays()) {
                if (day.getDate().toString().equals(d.getDate().toString())) {
                    Days.getInstance().getDays().remove(index);
                    String jsonDays = gson.toJson(d);
                    String key = d.getDate().toString();
                    Log.d("save",jsonDays);
                    Days.getInstance().addDay(d);
                    PreferenceService.saveData(key, jsonDays);
                } else index++;
            }
        }

    }

}
