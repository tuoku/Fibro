package com.example.fibro;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.*;
import com.google.gson.reflect.*;

/***
 * The Weather class is used to read weather data from OpenWeatherMap API
 * And contains methods that can be used from outside the class to access the data
 */
public class Weather { // TODO: Run networking on new Thread or AsyncTask

    double pressure;
    private double mLat;
    private double mLon;



    public Weather(double lat, double lon){ // constructor
        mLat = lat;
        mLon = lon;
    }
    /***
     * Converts JSON data into a Map using GSON
     * @param str JSON data
     * @return Map of the data
     */
    public static Map<String, Object> jsonToMap(String str){
        Map<String, Object> map = new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>(){}.getType());
        return map;
    }

    public void refresh(){

    }

    /***
     * Retrieves / updates weather data from OpenWeatherMap API
     * and stores the values in variables for later use.
     * Overrides run method from Thread to run networking in a thread other than main.
     */

    public void getData(){

        String urlString = "https://api.openweathermap.org/data/2.5/onecall?lat=" + mLat + "&lon=" + mLon + "&exclude=current,minutely,hourly" + "&appid=" + "71157a0f49ae0e66bc6ad5d523d8554b"; // API URL + location and API key TODO: variable location

        try{
            StringBuilder result = new StringBuilder();
            URL url = new URL(urlString);
            URLConnection conn = url.openConnection(); // Inits connection to API
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null){ // Adds JSON data from API line-by-line into a String
                result.append(line);
            }
            rd.close();
            Log.i("FIBRO", result.toString()); // Log raw data to Logcat
            Map<String, Object> respMap = jsonToMap(result.toString()); // Maps the raw data
            Map<String, Object> dayMap = jsonToMap(respMap.get("daily").toString()); // Maps data from category "main" to its own Map
            Map<String, Object> day0Map = jsonToMap(dayMap.get("0").toString());
            Map<String, Object> day1Map = jsonToMap(respMap.get("1").toString());
            Map<String, Object> day2Map = jsonToMap(respMap.get("2").toString());
            Map<String, Object> day3Map = jsonToMap(respMap.get("3").toString());


            pressure = Double.parseDouble(day0Map.get("pressure").toString()); // Assign value "pressure" from "main" category of Mapped data to variable
                                                                                // TODO: Read and store more information into other variables


        } catch(IOException e){
            Log.e("FIBRO",e.getMessage()); // Log possible errors to Logcat
        }

    }

    /***
     * Returns air pressure as String
     * @return Air pressure
     */
    public String getPressure(){
        return String.valueOf(pressure);
    }
}
