package com.example.mb.gps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.app.Activity;
import android.content.Context;
import android.widget.TextView;


public class GPS extends AppCompatActivity {


    Location location;
    Location[] localist = new Location[30];
    LocationListener localistener;
    int ind;
    LocationManager locationManager;


    public Location getLocation()
    {
        try
        {
            locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,localistener);
        }
        catch (Exception e)
        {

        }
        return location;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        final Button start = (Button) findViewById(R.id.start_button);
        getLocation();

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (start.getText() == "Start Tracking") {
                    start.setText("Stop Tracking");
                    localistener = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            localist[ind]=location;
                            Log.d("loca", localist[ind].toString());
                            ind++;
                            if(ind==30)
                            {
                                ind=0;
                            }
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    };
                }
                else{
                    start.setText("Start Tracking");
                    for(int i =0 ; i < 30; i++)
                    {
                        localist[i] = null;
                    }
                    ind = 0;
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_g, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
