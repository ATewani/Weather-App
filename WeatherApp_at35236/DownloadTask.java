/**
 * Created by tewanir on 11/30/17.
 */

package com.example.weatherapp;


import android.os.AsyncTask;
import android.util.JsonWriter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class DownloadTask extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... urls) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL(urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);

            int data = in.read();

            while (data != -1){
                char current = (char) data;
                result += current;
                data = reader.read();

            }

            return result;





        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonobj = new JSONObject(result);

            JSONObject weatherdata = new JSONObject(jsonobj.getString("currently"));

            int temp = (int) Double.parseDouble(weatherdata.getString("temperature"));
            String prec = (String) (weatherdata.getString("summary"));

            Double humidity = Double.parseDouble(weatherdata.getString("humidity"));
            Double windspeed = Double.parseDouble(weatherdata.getString("windSpeed"));

            MainActivity.temp.setText(String.valueOf(temp) + "°");
            MainActivity.prec.setText((prec));
            MainActivity.hum.setText(String.valueOf(humidity));
            MainActivity.wind.setText("Wind: " + String.valueOf(windspeed));

            //getting temp for next 5 hours
            Calendar current = Calendar.getInstance();
            int currenthour = current.get(Calendar.HOUR_OF_DAY);
            calc_hours(currenthour);

            JSONObject nextfivehours = new JSONObject(jsonobj.getString("hourly"));
            JSONArray data = nextfivehours.getJSONArray("data");

            MainActivity.h1temp.setText(String.valueOf((int) data.getJSONObject(1).getDouble("temperature")));
            MainActivity.h2temp.setText(String.valueOf((int) data.getJSONObject(2).getDouble("temperature")));
            MainActivity.h3temp.setText(String.valueOf((int) data.getJSONObject(3).getDouble("temperature")));
            MainActivity.h4temp.setText(String.valueOf((int) data.getJSONObject(4).getDouble("temperature")));
            MainActivity.h5temp.setText(String.valueOf((int) data.getJSONObject(5).getDouble("temperature")));

            //getting avg over 48 hours
            int avgtemp = 0;
            for (int i = 0; i < 48; i++){
                avgtemp += (int) data.getJSONObject(i).getDouble("temperature");
            }
            avgtemp = avgtemp/48;
            MainActivity.avg.setText("Average 48hr temp: " + avgtemp);

            //highs and lows for next week
            JSONObject weekforecast = new JSONObject(jsonobj.getString("daily"));
            JSONArray week = weekforecast.getJSONArray("data");

            int day = current.get(Calendar.DAY_OF_WEEK); //1-7
            week_temps(day, week);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void week_temps(int day, JSONArray week) throws Exception {
        Map<Integer, String> daysofweek = new HashMap<>();
        daysofweek.put(1, "SUN");
        daysofweek.put(2, "MON");
        daysofweek.put(3, "TUE");
        daysofweek.put(4, "WED");
        daysofweek.put(5, "THR");
        daysofweek.put(6, "FRI");
        daysofweek.put(7, "SAT");

        //set day text fields
        MainActivity.day1.setText(daysofweek.get(day+1 > 7 ? day-6 : day+1));
        MainActivity.day2.setText(daysofweek.get(day+2 > 7 ? day-5 : day+2));
        MainActivity.day3.setText(daysofweek.get(day+3 > 7 ? day-4 : day+3));
        MainActivity.day4.setText(daysofweek.get(day+4 > 7 ? day-3 : day+4));
        MainActivity.day5.setText(daysofweek.get(day+5 > 7 ? day-2 : day+5));
        MainActivity.day6.setText(daysofweek.get(day+6 > 7 ? day-1 : day+6));
        MainActivity.day7.setText(daysofweek.get(day));

        MainActivity.day1high.setText(String.valueOf((int) week.getJSONObject(1).getDouble("temperatureHigh")));
        MainActivity.day2high.setText(String.valueOf((int) week.getJSONObject(2).getDouble("temperatureHigh")));
        MainActivity.day3high.setText(String.valueOf((int) week.getJSONObject(3).getDouble("temperatureHigh")));
        MainActivity.day4high.setText(String.valueOf((int) week.getJSONObject(4).getDouble("temperatureHigh")));
        MainActivity.day5high.setText(String.valueOf((int) week.getJSONObject(5).getDouble("temperatureHigh")));
        MainActivity.day6high.setText(String.valueOf((int) week.getJSONObject(6).getDouble("temperatureHigh")));
        MainActivity.day7high.setText(String.valueOf((int) week.getJSONObject(7).getDouble("temperatureHigh")));

        MainActivity.day1low.setText(String.valueOf((int) week.getJSONObject(1).getDouble("temperatureLow")));
        MainActivity.day2low.setText(String.valueOf((int) week.getJSONObject(2).getDouble("temperatureLow")));
        MainActivity.day3low.setText(String.valueOf((int) week.getJSONObject(3).getDouble("temperatureLow")));
        MainActivity.day4low.setText(String.valueOf((int) week.getJSONObject(4).getDouble("temperatureLow")));
        MainActivity.day5low.setText(String.valueOf((int) week.getJSONObject(5).getDouble("temperatureLow")));
        MainActivity.day6low.setText(String.valueOf((int) week.getJSONObject(6).getDouble("temperatureLow")));
        MainActivity.day7low.setText(String.valueOf((int) week.getJSONObject(7).getDouble("temperatureLow")));

    }

    private void calc_temps(JSONArray hours) throws Exception {
        JSONObject obj = hours.getJSONObject(1);
        int t = (int) Double.parseDouble(obj.getString("temperature"));
        MainActivity.h1temp.setText(String.valueOf(t));

        obj = hours.getJSONObject(2);
        t = (int) Double.parseDouble(obj.getString("temperature"));
        MainActivity.h2temp.setText(String.valueOf(t));

        obj = hours.getJSONObject(3);
        t = (int) Double.parseDouble(obj.getString("temperature"));
        MainActivity.h3temp.setText(String.valueOf(t));

        obj = hours.getJSONObject(4);
        t = (int) Double.parseDouble(obj.getString("temperature"));
        MainActivity.h4temp.setText(String.valueOf(t));

        obj = hours.getJSONObject(5);
        t = (int) Double.parseDouble(obj.getString("temperature"));
        MainActivity.h5temp.setText(String.valueOf(t));

    }

    private void calc_hours(int currenthour) {
        if (currenthour + 1 > 12){
            MainActivity.hour1.setText(currenthour - 11 + "pm");
        }
        else MainActivity.hour1.setText(currenthour + 1 + "am");

        if (currenthour + 2 > 12){
            MainActivity.hour2.setText(currenthour - 10 + "pm");
        }
        else MainActivity.hour2.setText(currenthour + 2 + "am");

        if (currenthour + 3 > 12){
            MainActivity.hour3.setText(currenthour - 9 + "pm");
        }
        else MainActivity.hour3.setText(currenthour + 3 + "am");

        if (currenthour + 4 > 12){
            MainActivity.hour4.setText(currenthour - 8 + "pm");
        }
        else MainActivity.hour4.setText(currenthour + 4 + "am");

        if (currenthour + 5 > 12){
            MainActivity.hour5.setText(currenthour - 7 + "pm");
        }
        else MainActivity.hour5.setText(currenthour + 5 + "am");
    }
}
