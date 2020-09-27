package com.example.fibro;

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

    public void createDay(){
        Days.getInstance().getDays().add(new Day(date, mood, details, pressure));
    }

}
