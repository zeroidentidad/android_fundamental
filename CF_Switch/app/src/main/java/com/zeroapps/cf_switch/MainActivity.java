package com.zeroapps.cf_switch;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Switch aSwitch;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aSwitch=(Switch)findViewById(R.id.aswitch);
        mediaPlayer=MediaPlayer.create(this,R.raw.sound);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getApplicationContext(),"Encendida owo", Toast.LENGTH_LONG).show();
                    mediaPlayer.start();

                } else {
                    Toast.makeText(getApplicationContext(),"Apagada u_u", Toast.LENGTH_LONG).show();
                    mediaPlayer.release();
                }
            }
        });

    }
}