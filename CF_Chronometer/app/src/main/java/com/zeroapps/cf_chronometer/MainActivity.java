package com.zeroapps.cf_chronometer;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Chronometer chronometer;
    private long timePause;
    private boolean isStart;
    private boolean isPause;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer=(Chronometer)findViewById(R.id.chronometer);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnPause).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                long result = SystemClock.elapsedRealtime()-chronometer.getBase();
                Toast.makeText(getApplicationContext(), "time "+result, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStart:
                if(!isStart){
                    chronometer.setBase(SystemClock.elapsedRealtime()-timePause);
                    Log.i("test start",SystemClock.elapsedRealtime()+" - "+timePause);
                    long result= SystemClock.elapsedRealtime()-timePause;
                    Log.i("test start",result+"");
                    chronometer.start();
                    isStart=true;
                    isPause=false;
                }
                break;
            case R.id.btnPause:
                if (!isPause){
                    timePause = SystemClock.elapsedRealtime()-chronometer.getBase();
                    Log.i("test pause",SystemClock.elapsedRealtime()+" - "+chronometer.getBase());
                    Log.i("test pause",timePause+"");
                    chronometer.stop();
                    isPause=true;
                    isStart=false;
                }
                break;
            case R.id.btnStop:
                if (isPause){
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.stop();
                    timePause=0;
                } else {
                    Toast.makeText(getApplicationContext(), "Pausar tiempo", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}