package com.zeroapps.cf_spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner)findViewById(R.id.spinner);
        //ArrayAdapter<Student>adapter=new ArrayAdapter<>(this, R.layout.item_adapter,R.id.textItem, Student.getStudents());
        AdapterStudent adapter=new AdapterStudent(this, R.layout.item_adapter, Student.getStudents());
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}