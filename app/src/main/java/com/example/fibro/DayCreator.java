package com.example.fibro;

import android.util.Log;
import com.google.gson.Gson;
import java.sql.Date;

/**
 * Used to create a new instance of Day.
 * Information about day is collected during runtime
 * and when all the details are present a new instance of Day
 * is created and stored.
 * This is a singleton to allow collecting data from multiple classes during runtime.
 */
public class DayCreator {
    private Date date;
    private int mood;
    private String details;
    private double pressure;

    private static final DayCreator ourInstance = new DayCreator(); // Create THE instance

    /**
     * Return the DayCreator instance.
     * @return instance of DayCreator
     */
    public static DayCreator getInstance(){
        return ourInstance;
    }

    private DayCreator(){
    }

    /**
     * Set the date of the day that is to be created.
     * @param date date of the day
     */
    public void setDate(Date date){this.date = date;}

    /**
     * Set the mood of the day that is to be created.
     * @param mood mood of the day
     */
    public void setMood(int mood){this.mood = mood;}

    /**
     * Set the details of the day that is to be created.
     * @param details details of the day
     */
    public void setDetails(String details){this.details = details;}

    /**
     * Set the atmospheric pressure of the day that is to be created.
     * @param pressure atmospheric pressure of the day
     */
    public void setPressure(double pressure){this.pressure = pressure;}

    /**
     * Creates a new Day from the information collected during runtime
     * and adds the day to Days array.
     * If a day with the same date already exists in the array,
     * removes the old day and replaces with new.
     */
    public void createDay() {
        Gson gson = new Gson();
        Day d = new Day(date, mood, details, pressure); // creates a new day based on the information already given to DayCreator instance
        int index = 0;
        Boolean loggedAlready = false;
        for (Day dd : Days.getInstance().getDays()) { // Compares the newly created Day with existing ones
            if (dd.getDate().toString().equals(d.getDate().toString())) {
                loggedAlready = true; //set to true if found a match
            }
        }
        if (!loggedAlready) { // if didn't find a match from existing Days
            String jsonDays = gson.toJson(d); //turn the new Day into json string
            String key = d.getDate().toString(); //get the date of the Day, to be used as key value in SharedPreferences
            Log.d("save",jsonDays);
            Days.getInstance().addDay(d); // adds the new Day into the array of Days
            PreferenceService.saveData(key, jsonDays); // and save the day to SharedPreferences
        } else { // if did find a match from existing Days
            for (Day day : Days.getInstance().getDays()) { // search through the array to find the old Day
                if (day.getDate().toString().equals(d.getDate().toString())) { // if the current comparison is a match
                    Days.getInstance().getDays().remove(index); // delete the old day from array
                    String jsonDays = gson.toJson(d);
                    String key = d.getDate().toString();
                    Log.d("save",jsonDays);
                    Days.getInstance().addDay(d); // add the new day to array
                    PreferenceService.saveData(key, jsonDays); // old day is automatically overwritten in SharedPreferences due to same key value
                } else index++; //if current comparison wasn't a match try the next instance
            }
        }

    }

}
