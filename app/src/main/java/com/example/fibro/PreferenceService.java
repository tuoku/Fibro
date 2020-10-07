package com.example.fibro;

import java.util.Map;

/**
 * Class used to handle saving of data to SharedPreferences
 * and getting it back.
 */
public class PreferenceService {
    /**
     * Saves data to SharedPreferences
     * @param key key to use
     * @param json the value to save
     */
    public static void saveData(String key,String json){
        MainActivity.prefs.edit().putString(key,json).commit();
    }

    /**
     * Returns a Map of everything saved
     * @return Map
     */
    public static Map getAll(){
        return MainActivity.prefs.getAll();
    }
}
