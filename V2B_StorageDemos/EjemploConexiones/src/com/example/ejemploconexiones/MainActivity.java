package com.example.ejemploconexiones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	private Button mButton     = null;
	private EditText mEditText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		mButton   = (Button) findViewById(R.id.button);
		mEditText = (EditText) findViewById(R.id.editText);
		
		mButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String stringUrl = mEditText.getText().toString();
				
				if ( isNetworkAvailable() )
					obtainValueForCity("Barcelona");
				else 
					System.out.println("No hay conexión disponible");
			}
			
		});
		
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean isNetworkAvailable()  {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();
		if ( networkInfo != null && networkInfo.isConnected()) {
			return true;
		}
		return false;
	}

	public boolean isWifiConnection() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		boolean isWifi = networkInfo.isConnected();
		return isWifi;
	}

	public boolean isMobileConnection() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean isWifi = networkInfo.isConnected();
		return isWifi;
	}

	private void readStream( InputStream in) {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader( new InputStreamReader (in));
			String line = "";
			while ((line = reader.readLine())  != null) {
				System.out.println(line);
			}
		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (reader != null ) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private class DownloadURL extends AsyncTask<String, Void, String> {

		
		@Override
		protected String doInBackground(String... params) {
			try {
				URL url = new URL(params[0]);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				readStream(con.getInputStream());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return null;
		}

		@Override
		protected void onPostExecute(String result) {


			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {


			super.onPreExecute();
		}
		
		
	}
	
	private void connectionWithHttpClient() {
		HttpClient httpClient = new DefaultHttpClient();
		
		HttpPost httpPost = new HttpPost("http://www.example.com/login");
		
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add( new BasicNameValuePair("email","eenriquelopez@gmail.com"));
		nameValuePair.add( new BasicNameValuePair("password","123456"));
		
		try {
			HttpResponse response = httpClient.execute(httpPost);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void obtainValueForCity(String ciudad) {
		String url = "http://maps.google.com/maps/api/geocode/json?address="+ciudad+"&sensor=false";
		new DownloadURL().execute(url);
	}
}
