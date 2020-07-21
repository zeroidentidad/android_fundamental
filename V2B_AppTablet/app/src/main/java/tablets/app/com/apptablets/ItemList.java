package tablets.app.com.apptablets;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by booth05-mgr2 on 19/01/2017.
 */

public class ItemList extends AppCompatActivity {
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);
        textView = (TextView) findViewById(R.id.texto);
        Bundle bundle = getIntent().getExtras();

        if(bundle!= null){
            textView.setText(bundle.getString("nombrelista"));
        }
    }
}
