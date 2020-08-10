package com.zeroapps.cf_timepicker;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TimePicker;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        setHourMinute(23,11);
        Toast.makeText(getApplicationContext(), "Hora: "+getHourMinute(),Toast.LENGTH_LONG).show();

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Toast.makeText(getApplicationContext(), "Hora: "+hourOfDay+":"+minute,Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setHourMinute(int hour, int minute){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            timePicker.setHour(hour);
            timePicker.setMinute(minute);
        } else {
            timePicker.setCurrentHour(hour);
            timePicker.setCurrentMinute(minute);
        }
    }

    private String getHourMinute(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            return String.format("%02d:%02d", timePicker.getHour(), timePicker.getMinute());
        } else {
            return String.format("%02d:%02d", timePicker.getCurrentHour(), timePicker.getCurrentMinute());
        }
    }
}