package com.zeroapps.cf_radiogroup;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        radioGroup=(RadioGroup)findViewById(R.id.rgGen);
        textResult=(TextView)findViewById(R.id.txtResult);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)findViewById(checkedId);
                textResult.setText(radioButton.getText());
            }
        });

        RadioButton radioButton=(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
        Toast.makeText(getApplicationContext(), "Seleccionado "+radioButton.getText(),Toast.LENGTH_LONG).show();
    }
}