package com.zeroapps.v2b_notasapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private Button btnCreate;
    private LinearLayout layoutNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutNotes = (LinearLayout)findViewById(R.id.layoutNotes);

        btnCreate = (Button)findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SaveActivity.class);
                startActivity(i);
            }
        });
    }

    private void readNotes(){
        File fileDir = getFilesDir();
        for (String strFile : fileDir.list()){
            TextView noteText = new TextView(this);
            noteText.setText(strFile);
            noteText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT));

            layoutNotes.addView(noteText);
        }
    }
}