/*
package com.example.muradahmad.smartAgriculture;

import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

*/
/**
 * Created by muradahmad on 08/11/2018.
 *//*


public class WeatherDataFetcher {



    String OPEN_WEATHER_API = "http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=c0dab7df3167f4c291cb482c9a098d5c";


    public static JSONObject getJSON(Context context, String city){
        try {


            String result = "";


            URL url = new URL(URL[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();




            String line = "";

            while (line != null){

                line = bufferedReader.readLine();
                result = result + line;

            }
            InputStreamReader reader = new InputStreamReader(inputStream);

            int data = reader.read();
            while (data != -1) {
                char currentData = (char) data;

                result += currentData;
                data = reader.read();
            }


            JSONObject jsonObject = new JSONObject(result);

            JSONObject weatherData = new JSONObject(jsonObject.getString("main"));

            Double temperature = Double.parseDouble(weatherData.getString("temp"));
            // Double humidity = Double.parseDouble(weatherData.getString("humidity"));

            int temperatureInteger = (int) (temperature - 273.15);


            //objWeatherDatabse.insertData(String.valueOf(temperatureInteger));

            //  JSONObject weatherData1 = new JSONObject(jsonObject.getString("sys"));
            // String country = weatherData.getString("country");

            String placeName = jsonObject.getString("name");
            }

            //return data;
        }catch(Exception e){
            return null;
        }


*/
/*
    String URL = "http://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=c0dab7df3167f4c291cb482c9a098d5c";



        try {

            String result = "";


            URL url = new URL(URL[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();


            *//*
*/
/* BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line = "";

            while (line != null){

                line = bufferedReader.readLine();
                result = result + line;

            }*//*
*/
/*
            InputStreamReader reader = new InputStreamReader(inputStream);

            int data = reader.read();
            while (data != -1) {
                char currentData = (char) data;

                result += currentData;
                data = reader.read();
            }


            JSONObject jsonObject = new JSONObject(result);

            JSONObject weatherData = new JSONObject(jsonObject.getString("main"));

            Double temperature = Double.parseDouble(weatherData.getString("temp"));
            // Double humidity = Double.parseDouble(weatherData.getString("humidity"));

            int temperatureInteger = (int) (temperature - 273.15);


            //objWeatherDatabse.insertData(String.valueOf(temperatureInteger));

            //  JSONObject weatherData1 = new JSONObject(jsonObject.getString("sys"));
            // String country = weatherData.getString("country");

            String placeName = jsonObject.getString("name");








        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;


    }*//*



}*/
