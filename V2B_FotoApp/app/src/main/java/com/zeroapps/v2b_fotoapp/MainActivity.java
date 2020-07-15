package com.zeroapps.v2b_fotoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout mapa, carrete, categorias, ajustes, foto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapa = (LinearLayout) findViewById(R.id.mapa);
        carrete = (LinearLayout) findViewById(R.id.carrete);
        categorias = (LinearLayout) findViewById(R.id.categorias);
        ajustes = (LinearLayout) findViewById(R.id.ajutes);
        foto = (LinearLayout) findViewById(R.id.foto);

        mapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Kepedo",Toast.LENGTH_LONG).show();
            }
        });
    }
}