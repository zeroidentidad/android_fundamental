package com.zeroapps.cf_edittext;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.editTextName);
        editText.setText("Kepedo");
        Toast.makeText(getApplicationContext(), editText.getText().toString(), Toast.LENGTH_LONG).show();
        editText.setTextSize(40);
        editText.setBackgroundColor(Color.GREEN);
    }
}