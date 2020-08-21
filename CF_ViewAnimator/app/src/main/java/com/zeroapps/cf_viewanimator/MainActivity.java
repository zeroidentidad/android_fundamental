package com.zeroapps.cf_viewanimator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewAnimator;

public class MainActivity extends AppCompatActivity {
    private ViewAnimator viewAnimator;
    private EditText txtName;
    private EditText txtMessage;
    private TextView txtResult;
    private TextView txtSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewAnimator=(ViewAnimator) findViewById(R.id.viewAnimator);
        txtMessage=(EditText)findViewById(R.id.txtMessage);
        txtName=(EditText)findViewById(R.id.txtName);
        txtResult=(TextView) findViewById(R.id.txtResult);
        txtSend=(TextView)findViewById(R.id.txtSend);
        viewAnimator.setInAnimation(AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.slide_in_left));

        /*viewAnimator.removeViewAt(1);
        TextView textView = new TextView(this);
        textView.setText("Bienvenido");
        viewAnimator.addView(textView,1);*/
    }

    public void next (View view){
        if (viewAnimator.getCurrentView().getId()!=R.id.tres){
            String name = txtName.getText().toString();
            String message = txtMessage.getText().toString();
            if (!name.equals("")&&!message.equals("")){
                viewAnimator.showNext();
                txtResult.setText("Mensaje: "+message);
                txtSend.setText("Felicidades "+name);
            } else {
                Toast.makeText(getApplicationContext(),"Llenar todos lo campos",Toast.LENGTH_LONG).show();
            }
        }
    }

    public void previous (View view){
        if (viewAnimator.getCurrentView().getId()!=R.id.uno) {
            viewAnimator.showPrevious();
        }
    }
}