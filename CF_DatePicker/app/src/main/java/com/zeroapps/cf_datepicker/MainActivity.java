package com.zeroapps.cf_datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private DatePicker datePicker;
    private long dateMilis=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datePicker=(DatePicker) findViewById(R.id.datePicker);
        Toast.makeText(getApplicationContext(), "Mes "+datePicker.getMonth()+" Año "+datePicker.getYear(),Toast.LENGTH_LONG).show();

        SimpleDateFormat simpleDateFormat= new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date = simpleDateFormat.parse("2020/08/10");
            dateMilis = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        datePicker.setMaxDate(dateMilis);
        //datePicker.setMinDate(dateMilis);

        datePicker.init(2020, 7, 11, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Toast.makeText(getApplicationContext(), "Año "+year+" Mes "+monthOfYear+" Dia "+dayOfMonth,Toast.LENGTH_LONG).show();
            }
        });
    }
}