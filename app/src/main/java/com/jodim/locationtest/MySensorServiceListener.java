package com.jodim.locationtest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.util.Log;

import java.util.Arrays;

public class MySensorServiceListener implements SensorEventListener {

    private MySensorService service;

    public MySensorServiceListener(MySensorService service) {
        this.service = service;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //Log.e("sensor", "Listener on "+ sensor.getName());
    }

    public void onSensorChanged(SensorEvent event) {
        //Log.e("sensor", event.sensor.getName() + Arrays.toString(event.values));

        this.service.notifySensor(event);

        //this.activity.updateLocation(location);
    }
}
