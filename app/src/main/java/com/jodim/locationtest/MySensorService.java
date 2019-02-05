package com.jodim.locationtest;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public class MySensorService extends Service {

    public final static String ACTION_SENSOR = "sensor";

    private int[] sensors = {Sensor.TYPE_AMBIENT_TEMPERATURE, Sensor.TYPE_LIGHT, Sensor.TYPE_ACCELEROMETER};

    public MySensorService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        SensorManager sensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);

        MySensorServiceListener sensorServiceListener = new MySensorServiceListener(this);

        for (int sensor : this.sensors) {
            sensorManager.registerListener(sensorServiceListener, sensorManager.getDefaultSensor(sensor), SensorManager.SENSOR_DELAY_NORMAL);
        }

        /*try {
            sensorManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, sensorServiceListener);
        } catch (SecurityException ex) {
            Log.e("location", ex.getMessage());
        }*/

        return START_STICKY;
    }

    public void notifySensor(SensorEvent event) {
        Intent intent = new Intent(ACTION_SENSOR);

        switch (event.sensor.getType()) {
            case Sensor.TYPE_AMBIENT_TEMPERATURE:
                intent.putExtra("type", "temperature");
                intent.putExtra("degrees", event.values[0]);
                break;
            case Sensor.TYPE_LIGHT:
                intent.putExtra("type", "light");
                intent.putExtra("lux", event.values[0]);
                break;
            case Sensor.TYPE_ACCELEROMETER:
                intent.putExtra("type", "accelerometer");
                intent.putExtra("x", event.values[0]);
                intent.putExtra("y", event.values[1]);
                intent.putExtra("z", event.values[2]);
                break;
        }

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
