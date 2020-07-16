package com.zeroapps.v2b_fotoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PitureActivity extends AppCompatActivity {
    private static final String LOG_TAG = "AudioRecordTest";
    private MediaRecorder mediaRecorder = null;
    private static String mFileName = null;
    private MediaPlayer mediaPlayer = null;
    public static final int MY_PERMISSIONS_REQUEST_RECORD_AUDIO = 10;

    static int REQUEST_IMAGE_CAPTURE = 1;
    static int REQUEST_TAKE_PHOTO = 1;
    ImageView imageView;
    Intent intent = null;
    String mCurrentPhotoPath;
    LocationManager locationManager;
    Button play, stop, save, saveStop, saveAll;
    TextView lat, lon;
    String la, lo, categorias;
    Spinner spinner;

    // Internal Storage
    private SharedPreferences preferencesSettings;
    public static final int PREFERENCE_MODE_PRIVATE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piture);

        lat = findViewById(R.id.lat);
        lon = findViewById(R.id.lon);

        play = findViewById(R.id.play);
        //stop = findViewById(R.id.stop);
        save = findViewById(R.id.save);
        saveStop = findViewById(R.id.savestop);
        saveAll = findViewById(R.id.saveall);
        spinner = findViewById(R.id.spinner);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.categoria, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                categorias = spinner.getSelectedItem().toString();
                Toast.makeText(getApplicationContext(), categorias, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startRecording();
            }
        });

        saveStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopRecording();
            }
        });

        saveAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferencesSettings = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencesSettings.edit();
                editor.putString("long", lo);
                editor.putString("lat", la);
                editor.putString("image", mCurrentPhotoPath);
                editor.putString("music", mFileName);
                editor.putString("categoria", categorias);

                editor.commit();
            }
        });

        LocationListener locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                lat.setText(String.valueOf(location.getLatitude()));
                lon.setText(String.valueOf(location.getLongitude()));

                la = String.valueOf(location.getLatitude());
                lo = String.valueOf(location.getLongitude());

                Toast.makeText(getApplicationContext(), "Posición actual: " + String.valueOf(location.getLatitude()) + "," + String.valueOf(location.getLongitude()), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        locationManager.requestLocationUpdates(locationManager.GPS_PROVIDER, 4000, 10, locationListener);
    }

    private void dispatchTakePictureIntent(){
        Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
        if (intent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex){
                Log.v("ERROR", "Dispatch");
            }
            if (photoFile!=null){
                intent.putExtra("data", Uri.fromFile(photoFile));
                startActivityForResult(intent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPG"+timeStamp+"_";
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(imageFileName,".jpg", storageDir);

        mCurrentPhotoPath = "file:"+image.getAbsolutePath();
        return image;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode==REQUEST_IMAGE_CAPTURE&&resultCode==RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap imageBitmap= (Bitmap)extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }

    private void startRecording(){

        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!=PackageManager.PERMISSION_GRANTED){
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECORD_AUDIO)){
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
                }
            }
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},MY_PERMISSIONS_REQUEST_RECORD_AUDIO);
        } else {
            mediaRecorder=new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(mFileName);
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            try{
                mediaRecorder.prepare();
            }catch (IOException ex){
                Log.v("ERROR", "RECORD AUDIO");
            }
            mediaRecorder.start();
            Toast.makeText(getApplicationContext(), "Grabando", Toast.LENGTH_LONG).show();
        }
    }

    private void stopRecording(){
        mediaRecorder.stop();
        mediaRecorder.release();

        mediaRecorder=null;
        Toast.makeText(getApplicationContext(), "Terminado", Toast.LENGTH_SHORT).show();
    }
}