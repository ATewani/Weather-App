package com.example.weatherapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import org.json.JSONObject;
import org.w3c.dom.Text;


//public class MainActivity extends AppCompatActivity {
//
//    private Button b;
//    private TextView t;
//    private LocationManager locationManager;
//    private LocationListener listener;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        setContentView(R.layout.activity_main);
//
//        t = (TextView) findViewById(R.id.City);
//        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//
//
//        listener = new LocationListener() {
//            @Override
//            public void onLocationChanged(Location location) {
//                //t.setText("hello");
////                t.setText(location.getLongitude() + " " + location.getLatitude());
////                double longitude = location.getLongitude();
////                double latitude = location.getLatitude();
//                update_all(location);
//
//            }
//
//            @Override
//            public void onStatusChanged(String s, int i, Bundle bundle) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(String s) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String s) {
//
//                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                startActivity(i);
//            }
//        };
//
//        DownloadTask task = new DownloadTask();
//        task.execute("https://api.darksky.net/forecast/dbe14e9739345a778530ace9085eb192/30.2672,-97.7431");
//
//        get_loc();
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode){
//            case 10:
//                get_loc();
//                break;
//            default:
//                break;
//        }
//    }
//
//    void get_loc(){
//        // first check for permissions
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.INTERNET}
//                        ,10);
//            }
//            return;
//        }
//        // this code won't execute IF permissions are not allowed, because in the line above there is return statement.
//        locationManager.requestLocationUpdates("gps", 5000, 0, listener);
//    }
//
//    void update_all(Location location){
//        t.setText("Austin");
//
//
//
//
//
//
//    }
//
//}


public class MainActivity extends AppCompatActivity {


    static TextView temp;
    static TextView prec;
    static TextView city;
    static TextView hum;
    static TextView wind;
    static TextView avg;

    static TextView hour1;
    static TextView hour2;
    static TextView hour3;
    static TextView hour4;
    static TextView hour5;

    static TextView h1temp;
    static TextView h2temp;
    static TextView h3temp;
    static TextView h4temp;
    static TextView h5temp;

    static TextView day1;
    static TextView day2;
    static TextView day3;
    static TextView day4;
    static TextView day5;
    static TextView day6;
    static TextView day7;
    static TextView day1high;
    static TextView day2high;
    static TextView day3high;
    static TextView day4high;
    static TextView day5high;
    static TextView day6high;
    static TextView day7high;
    static TextView day1low;
    static TextView day2low;
    static TextView day3low;
    static TextView day4low;
    static TextView day5low;
    static TextView day6low;
    static TextView day7low;

    static EditText yearfield;
    static EditText monthfield;
    static EditText dayfield;
    static EditText hourfield;
    static EditText minfield;
    static EditText secfield;

    static Button button;
    static TextView timemachinetemp;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        temp = (TextView) findViewById(R.id.Temperature);
        prec = (TextView) findViewById(R.id.Precipitation);
        hum = (TextView) findViewById(R.id.Humidity);
        wind = (TextView) findViewById(R.id.Wind);
        avg = (TextView) findViewById(R.id.Average);

        hour1 = (TextView) findViewById(R.id.hour1);
        hour2 = (TextView) findViewById(R.id.hour2);
        hour3 = (TextView) findViewById(R.id.hour3);
        hour4 = (TextView) findViewById(R.id.hour4);
        hour5 = (TextView) findViewById(R.id.hour5);

        h1temp = (TextView) findViewById(R.id.h1temp);
        h2temp = (TextView) findViewById(R.id.h2temp);
        h3temp = (TextView) findViewById(R.id.h3temp);
        h4temp = (TextView) findViewById(R.id.h4temp);
        h5temp = (TextView) findViewById(R.id.h5temp);

        day1 = (TextView) findViewById(R.id.day1);
        day2 = (TextView) findViewById(R.id.day2);
        day3 = (TextView) findViewById(R.id.day3);
        day4 = (TextView) findViewById(R.id.day4);
        day5 = (TextView) findViewById(R.id.day5);
        day6 = (TextView) findViewById(R.id.day6);
        day7 = (TextView) findViewById(R.id.day7);

        day1high = (TextView) findViewById(R.id.day1high);
        day2high = (TextView) findViewById(R.id.day2high);
        day3high = (TextView) findViewById(R.id.day3high);
        day4high = (TextView) findViewById(R.id.day4high);
        day5high = (TextView) findViewById(R.id.day5high);
        day6high = (TextView) findViewById(R.id.day6high);
        day7high = (TextView) findViewById(R.id.day7high);

        day1low = (TextView) findViewById(R.id.day1low);
        day2low = (TextView) findViewById(R.id.day2low);
        day3low = (TextView) findViewById(R.id.day3low);
        day4low = (TextView) findViewById(R.id.day4low);
        day5low = (TextView) findViewById(R.id.day5low);
        day6low = (TextView) findViewById(R.id.day6low);
        day7low = (TextView) findViewById(R.id.day7low);

        yearfield = (EditText) findViewById(R.id.yearfield);
        monthfield = (EditText) findViewById(R.id.monthfield);
        dayfield = (EditText) findViewById(R.id.dayfield);
        hourfield = (EditText) findViewById(R.id.hourfield);
        minfield = (EditText) findViewById(R.id.minfield);
        secfield = (EditText) findViewById(R.id.secfield);
        button = (Button) findViewById(R.id.button);
        timemachinetemp = (TextView) findViewById(R.id.timemachinetemp);

        city = (TextView) findViewById(R.id.City);
        city.setText("Austin");


//        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
//        String provider = locationManager.getBestProvider(new Criteria(), false);
//
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//            return;
//        }
//        Location location = locationManager.getLastKnownLocation(provider);

//        Double lat = location.getLatitude();
//        Double lon = location.getLongitude();

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //verify inputs are correct
                String year = (yearfield.getText().toString());
                String month = (monthfield.getText().toString());
                String day = (dayfield.getText().toString());
                String hour = (hourfield.getText().toString());
                String min = (minfield.getText().toString());
                String sec = (secfield.getText().toString());

                String timemachinedate = "";
                timemachinedate += year + "-" + month + "-" + day + 'T' + hour + ":" + min + ":" + sec;
                ButtonTask button = new ButtonTask();
                button.execute(timemachinedate);



            }
        });







        DownloadTask task = new DownloadTask();
        task.execute("https://api.darksky.net/forecast/dbe14e9739345a778530ace9085eb192/30.2672,-97.7431");

    }


}




