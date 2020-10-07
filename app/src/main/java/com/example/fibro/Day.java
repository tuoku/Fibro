package com.example.fibro;

import java.sql.Date;

/**
 * Used to create instances that contain information about a specific day.
 * Information stored about day: date, mood, atmospheric pressure and
 * any additional details provided by user.
 */

public class Day {
    Date date;
    String details;
    int mood;
    double pressure;
// Just a regular constructor
    public Day(Date date, int mood, String details, double pressure){
        this.date = date;
        this.mood = mood;
        this.details = details;
        this.pressure = pressure;
    }

    /**
     * Returns the date of the instance.
     * @return Date of the day
     */
    public Date getDate(){return this.date;}

    /**
     * Returns the mood of the instance.
     * @return Mood as Integer between 1 and 5
     */
    public int getMood(){return this.mood;}

    /**
     * Returns the user provided details of the instance.
     * @return details as String
     */
    public String getDetails(){return this.details;}

    /**
     * Returns the atmospheric pressure of the instance.
     * @return pressure as Double
     */
    public double getPressure(){return this.pressure;}

    /**
     * Overrides toString method to return just the date as string
     * for the ArrayAdapter to generate items.
     * @return date as String
     */
    @Override
    public String toString(){
        return date.toString();
    }
}
