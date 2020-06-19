package com.zeroapps.cf_spinner;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
        ViewHolder holder=new ViewHolder();
        if (convertView==null){
            if (activity!=null){
                convertView=activity.getLayoutInflater().inflate(resource,null);

                holder.textItem = (TextView) convertView.findViewById(R.id.textItem);
                convertView.setTag(holder);
            }
        } else {
            holder=(ViewHolder)convertView.getTag();
        }

        Student student = students.get(position);
        holder.textItem.setText(student.getName());
        if (!student.isPass()){
            holder.textItem.setBackgroundColor(Color.RED);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder=new ViewHolder();
        if (convertView==null){
            if (activity!=null){
                convertView=activity.getLayoutInflater().inflate(resource,null);

                holder.textItem = (TextView) convertView.findViewById(R.id.textItem);
                convertView.setTag(holder);
            }
        } else {
            holder=(ViewHolder)convertView.getTag();
        }

        Student student = students.get(position);
        holder.textItem.setBackgroundColor(Color.GREEN);
        holder.textItem.setText(student.getName());
        if (!student.isPass()){
            holder.textItem.setBackgroundColor(Color.RED);
        }

        return convertView;
    }

    static class ViewHolder{
        private TextView textItem;
    }
}