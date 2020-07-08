package com.example.ejemploapp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	public static final String PREFS_NAME = "MiNombre";

	boolean mExternalStorageAvailable = false;
	boolean mExternalStorageWritable = false;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		readInternalStorage();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	private void createSharedPreferences() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putBoolean("ejemplo", true);

		//MUY IMPORTANTE
		editor.commit();
	}

	private void readSharedPreferences() {
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		boolean miValor = settings.getBoolean("ejemplo", false);

		if ( miValor ) {
			System.out.println("Encontrado");
		}
	}

	private void createInternalStorage() {
		try {
			OutputStreamWriter osw = new OutputStreamWriter( openFileOutput("files.txt",0 )) ;
			osw.write("Prueba");
			osw.flush();
			osw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void readInternalStorage() {
		try {
			BufferedReader br = new BufferedReader( new InputStreamReader( openFileInput("files.txt")));
			String str = br.readLine();
			System.out.println(str);
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private void checkStorage() {
		String state = Environment.getExternalStorageState();

		if (Environment.MEDIA_MOUNTED.equals(state)){
			//Todo correcto
			mExternalStorageAvailable = mExternalStorageWritable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			//Podremos leer los datos
			mExternalStorageAvailable = true;
			mExternalStorageWritable = false;

		} else {
			//No podremos ni leer ni escribir datos.
			mExternalStorageAvailable = mExternalStorageWritable = false;
		}

		if (mExternalStorageWritable == true ) {
			File path = new File (Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "MisImagenes");
			path.mkdirs();
			File file = new File(path, "prueba.png");

			if (file.exists())
				Log.d("ERROR","El archivo existe");

			InputStream is = getResources().openRawResource(R.drawable.ic_launcher);

			OutputStream os;
			try {
				os = new FileOutputStream(file);
				byte [] data = new byte [is.available()];
				is.read(data);
				os.write(data);
				is.close();
				os.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
