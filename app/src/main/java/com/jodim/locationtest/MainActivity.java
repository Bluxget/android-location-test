package com.jodim.locationtest;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int PERMISSIONS_REQUEST_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

        /*LocationManager locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);

        MyLocationServiceListener locationServiceListener = new MyLocationServiceListener(new MyLocationService());

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSIONS_REQUEST_FINE_LOCATION);
            }
        }*/

        Intent intent = new Intent(this, MyLocationService.class);

        /*try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationServiceListener);
        } catch(SecurityException ex) {
            Log.e("location", ex.getMessage());
        }*/

        startService(intent);

        LocalBroadcastManager.getInstance(this).registerReceiver(new ReceiverMainActivity(this), new IntentFilter("location"));
    }

    public void displayLocation(double latitude, double longitude, double altitude) {
        final TextView latitude_text = findViewById(R.id.latitude);
        latitude_text.setText("Latitude : "+ latitude);

        final TextView longitude_text = findViewById(R.id.longitude);
        longitude_text.setText("Longitude : "+ longitude);

        final TextView altitude_text = findViewById(R.id.altitude);
        altitude_text.setText("Altitude : "+ altitude);
    }
}
