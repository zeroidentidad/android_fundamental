package com.zeroapps.firstapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout linearLayout=new LinearLayout(getApplicationContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        //setContentView(R.layout.activity_main);
        linearLayout.setBackgroundColor(Color.GREEN);
        setContentView(linearLayout);

        LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        TextView textView=new TextView(getApplicationContext());
        textView.setText("kepedo prro");
        //textView.setLayoutParams(params);
        textView.setBackgroundColor(Color.RED);
        linearLayout.addView(textView, params);
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