package com.example.weatherapp;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tewanir on 12/3/17.
 */

public class ButtonTask extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {
        String result = "";
        URL url;
        HttpURLConnection urlConnection = null;

        try {
            url = new URL("https://api.darksky.net/forecast/dbe14e9739345a778530ace9085eb192/30.2672,-97.7431," + params[0]);
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
          //  e.printStackTrace();
        }


        return "invalid entry";
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.equals("invalid entry")){
            MainActivity.timemachinetemp.setText("ER");
            return;
        }



        try {
            JSONObject jsonobj = new JSONObject(result);
            JSONObject weatherdata = new JSONObject(jsonobj.getString("currently"));

            int temp = (int) Double.parseDouble(weatherdata.getString("temperature"));

            MainActivity.timemachinetemp.setText(String.valueOf(temp + "°"));






        } catch (JSONException e) {
            MainActivity.timemachinetemp.setText("XX");
          //  e.printStackTrace();
        }


    }
}
