package com.zeroapps.cf_progressbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private int progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar=(ProgressBar)findViewById(R.id.progressBar2);
    }

    public void add(View view){
        Button btnAdd = (Button)view;
        progress=progressBar.getProgress();
        progress=progress+10;
        if (progress<100){
            progressBar.setProgress(progress);
            btnAdd.setText(progress+"%");
        }else{
            btnAdd.setText("Completado "+progress+"%");
            progressBar.setProgress(0);
        }
    }
}