package com.zeroapps.cf_checkboxradiobutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBox;
    private RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBox=(CheckBox)findViewById(R.id.checkBox);
        radioButton=(RadioButton)findViewById(R.id.radioButton);
        if (checkBox.isChecked()){
            Toast.makeText(getApplicationContext(),"Seleccionado"+checkBox.getText(), Toast.LENGTH_LONG).show();
        }
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Seleccionado"+checkBox.getText(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"No seleccionado", Toast.LENGTH_LONG).show();
                }
            }
        });

        radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    Toast.makeText(getApplicationContext(),"Seleccionado"+radioButton.getText(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),"No seleccionado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}