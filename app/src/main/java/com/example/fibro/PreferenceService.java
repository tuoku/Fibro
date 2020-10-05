package com.example.fibro;

import android.util.Log;

import java.sql.Date;
import java.util.Map;

public class PreferenceService {

    public static void setIndex(){
        MainActivity.prefs.edit().putInt("INDEX",Days.getInstance().getIndex()).commit();
    }
    public static int getIndex(){
        return MainActivity.prefs.getInt("INDEX",0);
    }

    public static void saveData(String json){
        int i = Days.getInstance().getIndex();
        MainActivity.prefs.edit().putString(String.valueOf(i),json).commit();
        Log.d("saveIndex", String.valueOf(i));
        Days.getInstance().setIndex(i+1);

    }

    public static String getByIndex(String i){
        return MainActivity.prefs.getString(i, "0");
    }

    public static Map getAll(){
        return MainActivity.prefs.getAll();
    }

    public void reset(){
        MainActivity.prefs.edit().clear();
    }
}
