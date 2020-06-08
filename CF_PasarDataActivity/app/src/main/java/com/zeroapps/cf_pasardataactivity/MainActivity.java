package com.zeroapps.cf_pasardataactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShow=(Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // forma explicita
                /*Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("age",27);
                intent.putExtra("welcome","Kepedo");
                intent.putExtra("relaxed",false);
                startActivity(intent);*/

                // forma implicita
                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT,"Texto X");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Enviar texto a"));
            }
        });
    }
}