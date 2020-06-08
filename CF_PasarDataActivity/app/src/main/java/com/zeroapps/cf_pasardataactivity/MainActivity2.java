package com.zeroapps.cf_pasardataactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle bundle=getIntent().getExtras();
        if (bundle!=null){
            String welcome=bundle.getString("welcome","");
            int age=bundle.getInt("age",0);
            boolean relaxed=bundle.getBoolean("relaxed",true);

            Toast.makeText(getApplicationContext(), welcome+" "+age+" "+relaxed,Toast.LENGTH_LONG).show();
        }
    }
}