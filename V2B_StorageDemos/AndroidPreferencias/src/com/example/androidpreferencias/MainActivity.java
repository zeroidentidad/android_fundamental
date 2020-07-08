package com.example.androidpreferencias;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int RESULTADO_SETTINGS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	
		switch (item.getItemId()) {
		case R.id.menu_settings:
			Intent i = new Intent (this, UserSettingsActivity.class);
			startActivityForResult(i, RESULTADO_SETTINGS);
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {
		case RESULTADO_SETTINGS:
			showUserSettings();
			break;
		}
	}
    
    
	private void showUserSettings() {
		SharedPreferences mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
		
		StringBuilder mBuilder = new StringBuilder();
		
		mBuilder.append("\n Nombre de usuario: " + mSharedPreferences.getString("prefUsername", "NULL"));
		
		mBuilder.append("\n Reporte: " + mSharedPreferences.getBoolean("prefSendReport", false));
		
		mBuilder.append("\n Frecuencia de Sincronización: " + mSharedPreferences.getString("prefSyncFrequency", "NULL"));
	
		TextView mTextView = (TextView) findViewById(R.id.campoText);
		
		mTextView.setText(mBuilder.toString());
	}
    
}
