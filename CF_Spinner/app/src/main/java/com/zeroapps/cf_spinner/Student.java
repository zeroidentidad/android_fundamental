package com.zeroapps.cf_spinner;

import java.util.ArrayList;

public class Student {
    private int id;
    private String name;
    private boolean isPass;

    public Student(int id, String name, boolean isPass){
        this.id=id;
        this.name=name;
        this.isPass=isPass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isPass() {
        return isPass;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ArrayList<Student> getStudents(){
        ArrayList<Student> students=new ArrayList<>();
        students.add(new Student(1,"Jesus",true));
        students.add(new Student(2,"Karent",true));
        students.add(new Student(3,"Veronica",false));
        students.add(new Student(4,"Karla",true));
        return students;
    }
}