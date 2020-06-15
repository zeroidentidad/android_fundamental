package com.zeroapps.cf_button_togglebutton;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnCancel;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCancel=(Button)findViewById(R.id.btnCancel);
        btnSave=(Button)findViewById(R.id.btnSave);

        btnCancel.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        /*btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_LONG).show();
            }
        });*/
    }

    /*public void save(View view){
        Button button = (Button)view;
        //button.setEnabled(false);
        Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_LONG).show();
        btnCancel.setTextColor(Color.RED);
    }*/

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave :
                Toast.makeText(getApplicationContext(), "Guardado", Toast.LENGTH_LONG).show();
                break;

            case R.id.btnCancel :
                Toast.makeText(getApplicationContext(), "Cancelado", Toast.LENGTH_LONG).show();
                break;
        }
    }
}