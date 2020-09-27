package com.example.fibro;

import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.*;
import com.google.gson.reflect.*;

public class Days {
    private List<Day> days;
    private static final Days ourInstance = new Days();
    private int index = 0;

    public static Days getInstance(){
        return ourInstance;
    }

    private Days(){
        days = new ArrayList<>();
    }

    public List<Day> getDays(){
        return days;
    }



}
