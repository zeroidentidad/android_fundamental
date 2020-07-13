package com.zeroapps.cf_listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView listvStudents;
    private ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listvStudents=(ListView)findViewById(R.id.listvStudents);

        students = Student.getStudents();
        final AdapterStudent adapterStudent = new AdapterStudent(this,R.layout.item_adapter, students);
        listvStudents.setAdapter(adapterStudent);
        listvStudents.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) adapterStudent.getItem(position);
                Toast.makeText(getApplicationContext(), "Seleccionado: "+student.getName(), Toast.LENGTH_LONG).show();
            }
        });
        listvStudents.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) adapterStudent.getItem(position);
                Toast.makeText(getApplicationContext(), student.getName()+ " dice que te ama :v", Toast.LENGTH_LONG).show();
                return false; //mantener enfasis select
            }
        });
    }
}