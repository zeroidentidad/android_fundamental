package com.example.ejemplociclodevida;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("onDestroy");
	}



	@Override
	protected void onPause() {
		super.onPause();
		System.out.println("onPause");
	}



	@Override
	protected void onRestart() {
		super.onRestart();
		System.out.println("onRestart");
	}



	@Override
	protected void onResume() {
		super.onResume();
		System.out.println("onResume");
	}


	@Override
	protected void onStart() {
		super.onStart();
		System.out.println("onStart");
	}



	@Override
	protected void onStop() {
		super.onStop();
		System.out.println("onStop");
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
