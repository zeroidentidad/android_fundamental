package com.zeroapps.v2b_notasapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveActivity extends AppCompatActivity {
    private EditText editTextNote;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.write_note);

        editTextNote = (EditText)findViewById(R.id.editTextNote);

        btnSave = (Button)findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long unixTime = System.currentTimeMillis()/1000L;
                String fileName = String.valueOf(unixTime);
                try {
                    saveOnFile(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void saveOnFile(String fileName) throws IOException {
        FileOutputStream fileOutputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
        fileOutputStream.write(editTextNote.getText().toString().getBytes());
        fileOutputStream.close();
    }
}