package com.zeroapps.cf_imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
    }

    public void image(View view){
        imageView.setImageResource(R.drawable.ic_baseline_accessibility_24);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
    }
}