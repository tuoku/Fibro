package com.example.fibro;

import android.os.StrictMode;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.TextView;

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
public class Weather implements Runnable { // TODO: Run networking on new Thread or AsyncTask

    double pressure;

    public Weather(){ // constructor
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
        //Thread thread = new Thread(this);
        //thread.start();
        run();
    }

    /***
     * Retrieves / updates weather data from OpenWeatherMap API
     * and stores the values in variables for later use.
     * Overrides run method from Thread to run networking in a thread other than main.
     */
    //@Override
    public void run(){

        String urlString = "https://api.openweathermap.org/data/2.5/weather?q=" + "Espoo" + "&appid=" + "71157a0f49ae0e66bc6ad5d523d8554b"; // API URL + location and API key TODO: variable location

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
            Map<String, Object> mainMap = jsonToMap(respMap.get("main").toString()); // Maps data from category "main" to its own Map

            setPressure(Double.parseDouble(mainMap.get("pressure").toString())); // Assign value "pressure" from "main" category of Mapped data to variable
            Log.d("PRESSURE",getPressure());                                                                    // TODO: Read and store more information into other variables


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
    public void setPressure(double p){this.pressure = p;}
    public Boolean isLowPressure(){
        if(pressure < 1013.0){
            return true;
        }else return false;
    }
}
