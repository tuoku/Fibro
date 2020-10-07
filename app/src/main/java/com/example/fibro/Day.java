package com.example.fibro;

import java.sql.Date;
import java.util.Calendar;

public class Day {
    Date date;
    String details;
    int mood;
    double pressure;

    public Day(Date date, int mood, String details, double pressure){
        this.date = date;
        this.mood = mood;
        this.details = details;
        this.pressure = pressure;
    }
    public void setDate(Date date){this.date = date;}
    public Date getDate(){return this.date;}
    public void setMood(int mood){this.mood = mood;}
    public int getMood(){return this.mood;}
    public void setDetails(String details){this.details = details;}
    public String getDetails(){return this.details;}
    public void setPressure(double pressure){this.pressure = pressure;}
    public double getPressure(){return this.pressure;}


    @Override
    public String toString(){
        return date.toString();
    }
}
