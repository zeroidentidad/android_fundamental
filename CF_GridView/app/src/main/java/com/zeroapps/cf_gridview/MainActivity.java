package com.zeroapps.cf_gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridView = (GridView) findViewById(R.id.gridView);
        final AdapterStudent adapterStudent= new AdapterStudent(this,R.layout.item_adapter,Student.getStudents());
        gridView.setAdapter(adapterStudent);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = adapterStudent.getItem(position);
                Toast.makeText(getApplicationContext(), "Alumno: "+student.getName(), Toast.LENGTH_LONG).show();
            }
        });
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = adapterStudent.getItem(position);
                Toast.makeText(getApplicationContext(), "Alumno: "+student.getName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
}