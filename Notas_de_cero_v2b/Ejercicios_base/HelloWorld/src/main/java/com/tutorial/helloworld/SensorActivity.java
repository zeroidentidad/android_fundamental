package com.tutorial.helloworld;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class SensorActivity  extends Activity implements SensorEventListener{

    private SensorManager mSensorManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        List<Sensor> sensorList = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        for ( Sensor sensor : sensorList) {
            Log.d("Sensor",sensor.getName());
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        synchronized (this) {
            switch ( sensorEvent.sensor.getType()) {
                case Sensor.TYPE_ACCELEROMETER:
                    for (int i = 0 ; i<3;i++) {
                        Log.d("SensorActivity",String.valueOf(sensorEvent.values[i]));
                    }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
