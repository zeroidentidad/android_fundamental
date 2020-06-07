package com.zeroapps.cf_activitylaunchmode;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class A extends AppCompatActivity implements View.OnClickListener{
    protected StringBuilder texto;
    protected TextView textViewTexto;

    public void imprimirEstado(String estado) {
        texto.append(this.getClass().getSimpleName() + ": " + estado + "\n");
        textViewTexto.setText(texto.toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (this.getClass().getSimpleName()) {
            case "A" :
                setContentView(R.layout.activity_a);
                break;
            case "B" :
                setContentView(R.layout.activity_b);
                break;
            case "C" :
                setContentView(R.layout.activity_c);
                break;
            case "D" :
                setContentView(R.layout.activity_d);
                break;
        }

        this.texto = new StringBuilder();
        this.textViewTexto = (TextView) findViewById(R.id.textViewTexto);

        imprimirEstado("Create");
        findViewById(R.id.btnA).setOnClickListener(this);
        findViewById(R.id.btnB).setOnClickListener(this);
        findViewById(R.id.btnC).setOnClickListener(this);
        findViewById(R.id.btnD).setOnClickListener(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        imprimirEstado("NewIntent");
    }

    @Override
    protected void onStart() {
        super.onStart();
        imprimirEstado("Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        imprimirEstado("Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        imprimirEstado("Pause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        imprimirEstado("Stop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        imprimirEstado("Restart");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnA:
                abrirActividad(A.class);
                break;
            case R.id.btnB:
                abrirActividad(B.class);
                break;
            case R.id.btnC:
                abrirActividad(C.class);
                break;
            case R.id.btnD:
                abrirActividad(D.class);
                break;
        }
    }

    public void abrirActividad(Class c){
        startActivity(new Intent(getApplicationContext(),c));
    }

}