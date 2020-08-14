package com.zeroapps.cf_videoview;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
    private VideoView videoView;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView=(VideoView)findViewById(R.id.videoView);

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);

        //raw local
        //String path="android.resource://"+getPackageName()+"/"+R.raw.smallvideo;
        //videoView.setVideoURI(Uri.parse(path));

        videoView.setVideoPath("https://demonuts.com/Demonuts/smallvideo.mp4");

        //videoView.start();

        videoView.seekTo(2000);
        videoView.pause();

        videoView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(videoView.isPlaying()){
                    videoView.pause();
                } else {
                    videoView.start();
                    Toast.makeText(getApplicationContext(),"Tiempo actual: "+videoView.getCurrentPosition(),Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(),"Duración: "+videoView.getDuration(),Toast.LENGTH_LONG).show();
            }
        });

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                videoView.start();
            }
        });
    }

    public void stop(View view){
        videoView.stopPlayback();
    }
}