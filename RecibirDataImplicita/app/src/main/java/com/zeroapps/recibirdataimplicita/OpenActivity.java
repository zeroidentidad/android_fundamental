package com.zeroapps.recibirdataimplicita;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class OpenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            Toast.makeText(getApplicationContext(),"Extra "+bundle.getString(Intent.EXTRA_TEXT), Toast.LENGTH_LONG).show();
        }
    }
}