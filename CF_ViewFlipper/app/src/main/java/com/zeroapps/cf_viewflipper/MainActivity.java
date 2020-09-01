package com.zeroapps.cf_viewflipper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    private ViewFlipper viewFlipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper=(ViewFlipper)findViewById(R.id.viewFlipper);
        //viewFlipper.setAutoStart(false);
    }

    public void next(View view){
        viewFlipper.showNext();
        viewFlipper.stopFlipping();
    }

    public void previous(View view){
        viewFlipper.showPrevious();
        viewFlipper.startFlipping();
    }
}