package com.zeroapps.cf_tabhost;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TabHost tabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();
        newTab("tab1", R.id.tab1, "Tab1", getIcon(R.drawable.list));
        newTab("tab2", R.id.tab2, "Tab2", getIcon(R.drawable.fail));
        newTab("tab3", R.id.tab3, "", getIcon(R.drawable.apro));
        tabHost.setCurrentTab(2);

        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Toast.makeText(getApplicationContext(), "id: "+tabId, Toast.LENGTH_LONG).show();
            }
        });
    }

    private void newTab(String tag, int idContent, String title, Drawable drawable){
        TabHost.TabSpec spec = tabHost.newTabSpec(tag);
        spec.setContent(idContent);
        spec.setIndicator(title, drawable);
        tabHost.addTab(spec);
    }

    private Drawable getIcon(int idDrawable){
        return ContextCompat.getDrawable(getApplicationContext(),idDrawable);
    }
}