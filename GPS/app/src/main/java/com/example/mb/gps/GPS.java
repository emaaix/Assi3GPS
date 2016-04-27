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
    public static Location[] localist = new Location[30];
    LocationListener localistener;
    public static int ind;
    LocationManager locationManager;
    TextView t1;
    public static TextView t2;
    TextView t3;
    TextView t4;
    boolean act = false;
    Button start;
    int clock = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);


        start = (Button) findViewById(R.id.start_button);
        t1= (TextView) findViewById(R.id.textView);
        t2= (TextView) findViewById(R.id.textView2);
        t3= (TextView) findViewById(R.id.textView3);
        t4= (TextView) findViewById(R.id.textView4);



        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    if (start.getText() == "Start Tracking") {
                        start.setText("Stop Tracking");
                        t4.setText("GPS Active");
                        try {
                            localistener = new LocationListener() {

                                @Override
                                public void onLocationChanged(Location location) {
                                    clock++;
                                    t1.setText("Overall Time : " + clock + "s");
                                    t3.setText("Current Speed : " + location.getSpeed());
                                    localist[ind] = location;
                                    ind++;
                                    if (ind == 30) {
                                        ind = 0;
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
                        } catch (Exception e) {
                            Log.e("EFG", "falbala");
                        }
                        try {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, localistener);
                        } catch (Exception e) {
                        }
                }
                else{
                    start.setText("Start Tracking");
                    t4.setText("GPS Inactive");
                    t3.setText("Current Speed : N/A");
                    t2.setText("Averrage Speed : N/A");
                    t1.setText("Overall Time : 0s");
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
