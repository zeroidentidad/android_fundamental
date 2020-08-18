package com.zeroapps.cf_imageglide;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView=(ImageView)findViewById(R.id.imageView);
        Glide.with(getApplicationContext()).load("https://i.redd.it/u97gxjvk9lh51.jpg")
                .placeholder(R.drawable.ic_launcher_background)
                .override(150,150)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageView);
    }
}