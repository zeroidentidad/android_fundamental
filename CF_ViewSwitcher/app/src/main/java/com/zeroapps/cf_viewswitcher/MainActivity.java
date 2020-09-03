package com.zeroapps.cf_viewswitcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {
    ViewSwitcher viewSwitcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewSwitcher=(ViewSwitcher)findViewById(R.id.viewSwitcher);

        TextView textView=new TextView(MainActivity.this);
        textView.setText("primer vista code");
        viewSwitcher.addView(textView);

        TextView textView2=new TextView(MainActivity.this);
        textView.setText("otra vista code");
        viewSwitcher.addView(textView2);
        //viewSwitcher.removeViewAt(1);

        /* metodo factory
        viewSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView=new TextView(MainActivity.this);
                if (viewSwitcher.getChildCount()!=1){
                    textView.setText("primer vista code");
                } else {
                    textView.setText("segunda vista code");
                }
                return textView;
            }
        });*/
    }

    public void next(View view){
        viewSwitcher.showNext();
    }

    public void previous(View view){
        viewSwitcher.showPrevious();
    }
}