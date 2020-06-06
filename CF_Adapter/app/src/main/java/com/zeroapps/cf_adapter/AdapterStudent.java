package com.zeroapps.cf_adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterStudent extends ArrayAdapter {
    private Activity activity;
    ArrayList<Student> students;
    private  int resource;
    public AdapterStudent(@NonNull Activity activity, int resource, ArrayList<Student> students) {
        super(activity, resource, students);
        this.activity=activity;
        this.resource=resource;
        this.students=students;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Object getItem(int position) {
        return students.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (activity!=null){
            convertView=activity.getLayoutInflater().inflate(resource,null);
            TextView txtItem = (TextView) convertView.findViewById(R.id.textItem);
            Student student = students.get(position);
            txtItem.setText(student.getName());
            if (!student.isPass()){
                txtItem.setBackgroundColor(Color.RED);
            }
        }
        return convertView;
    }
}
