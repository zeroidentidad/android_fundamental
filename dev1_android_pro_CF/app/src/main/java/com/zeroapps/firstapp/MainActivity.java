package com.zeroapps.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        test();
    }

    public void  test(){
        Log.e("test e", "kepedo e :v");
        Log.w("test w", "kepedo w :v");
        Log.i("test i", "kepedo i :v");
        Log.d("test d", "kepedo d :v");
        Log.v("test d", "kepedo v :v");
    }
}