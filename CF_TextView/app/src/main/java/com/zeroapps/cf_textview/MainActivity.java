package com.zeroapps.cf_textview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TextView txtWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        Toast.makeText(getApplicationContext(),"Texto actual: "+txtWelcome.getText(),Toast.LENGTH_LONG).show();
        txtWelcome.setText("nuevo texto reemplazado");
        //Toast.makeText(getApplicationContext(),"Texto actual: "+txtWelcome.getText(),Toast.LENGTH_LONG).show();
        txtWelcome.setTextColor(Color.RED);
    }
}