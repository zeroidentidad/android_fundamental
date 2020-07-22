package app.imagenes.my.com.appimagenes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.image);

        Picasso.with(this).load("http://2.bp.blogspot.com/-hCPaCQUSnKA/VnsTPmuSlwI/AAAAAAAADo8/gmOjVgjhbqw/s1600/android-error.png").
                placeholder(R.drawable.android).
                error(R.drawable.android_error).
                resize(50,50)
                .into(imageView);
     }
}
