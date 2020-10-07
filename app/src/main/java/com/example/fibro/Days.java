package com.example.fibro;

import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import com.google.gson.*;

/**
 * Class responsible for fetching saved Day instances from SharedPreferences
 * and keeping an array of all existing Days.
 * Singleton to keep Days array accessible to every class.
 */
public class Days {
    private ArrayList<Day> days;
    private static final Days ourInstance = new Days();
    private int index;

    Gson gson = new Gson();

    /**
     * Returns the singleton
     * @return Days singleton
     */
    public static Days getInstance() {
        return ourInstance;
    }

    private Days() {
        days = new ArrayList<>();
    }

    /**
     * Returns the array of Days
     * @return Days array
     */
    public ArrayList<Day> getDays() {
        return days;
    }

    /**
     * Gets days from SharedPreferences and adds them to days array
     */
    public void fetchDaysFromPrefs() {
        Map<String, String> resMap = PreferenceService.getAll(); //Gets all data from SharedPreferences as Map
        Iterator it = resMap.entrySet().iterator(); //make a iterator to traverse the map
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            if (!(pair.getKey().equals("INDEX"))) { //SharedPreferences used to hold index number with key INDEX and we don't want that in our array
                String s = pair.getValue().toString(); // get the json String from the entry
                String ss = s.replace("[", ""); // For some reason gson always returns an array with a single object inside
                String sss = ss.replace("]", "");// so we strip the "[" and "]" to make it just an object
                Day d = gson.fromJson(sss, Day.class); //Recreate the Day from json
                days.add(d);// and add it to our array
                Log.d("FETCHDAYS", "Added " + pair.getKey());
            }
        }
        Log.d("FETCHDAYS", "Added all Days to ArrayList");
        sortArray(days); //Sort the array in ASC
        Log.d("FETCHDAYS", "Sorted ArrayList");
    }

    /**
     * Method for sorting the days in an array by date, ascending,
     * since MPAndroidChart only accepts arrays sorted as such
     * @param arrayList the array to be sorted
     */
    private void sortArray(ArrayList<Day> arrayList) {
        if (arrayList != null) { //check that there is anything to sort
            Collections.sort(arrayList, new Comparator<Day>() {
                @Override                                   //Override the compare method from Collections
                public int compare(Day o1, Day o2) {        //to compare the dates of our Days
                    return o1.getDate().compareTo(o2.getDate());
                }
            });
        }
    }

    /**
     * Returns index
     * @return index
     */
    public int getIndex() { // Useless btw
        return index;
    }

    /**
     * Sets the index to specified value
     * @param i Index to save
     */
    public void setIndex(int i) { // Useless too
        this.index = i;
    }

    /**
     * Adds a Day to the days array
     * @param d Day to add
     */
    public void addDay(Day d) {
        days.add(d);
    }

    /**
     * Returns a specific Day from the array
     * @param index Index of day in array
     * @return the requested Day
     */
    public Day getDay(int index){
        return days.get(index);
    }
}