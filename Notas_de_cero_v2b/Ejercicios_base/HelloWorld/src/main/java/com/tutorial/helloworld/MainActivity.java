package com.tutorial.helloworld;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.provider.Contacts;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends ActionBarActivity {

    private HelloBroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("HelloWorld","onCreate");

        //Intent i = new Intent(this, SecondActivity.class);
        //i.putExtra("valorTest","aleatorio");

        //startActivity(i);
        //access();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("HelloWorld","onRestart");
    }

    @Override
    protected void onPause() {
        unregisterReceiver(mReceiver);
        super.onPause();
        Log.d("HelloWorld","onPause");
    }

    @Override
    protected void onResume() {
        this.mReceiver = new HelloBroadcastReceiver();
        registerReceiver(this.mReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        super.onResume();
        Log.d("HelloWorld","onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("HelloWorld","onStart");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("HelloWorld","onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void access() {
        ContentResolver cr = getContentResolver();
        Cursor cur = cr.query(Contacts.People.CONTENT_URI,
                null, null, null, null);
        if (cur.getCount() > 0) {
            while (cur.moveToNext()) {
                String id = cur.getString(cur.getColumnIndex(Contacts.People._ID));
                String name = cur.getString(cur.getColumnIndex(Contacts.People.DISPLAY_NAME));
                Log.d("ContentProvider",name);
            }
        }
    }
}
