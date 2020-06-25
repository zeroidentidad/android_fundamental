package com.zeroapps.cf_quickcontactbadge;

import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.QuickContactBadge;

public class MainActivity extends AppCompatActivity {
    private QuickContactBadge email;
    private QuickContactBadge phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email=(QuickContactBadge)findViewById(R.id.email);
        phone=(QuickContactBadge)findViewById(R.id.phone);

        email.assignContactFromEmail("zero@mail.com", true);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN_MR2){
            Bundle bundle = new Bundle();
            bundle.putString(ContactsContract.Intents.Insert.NAME, "Zero");
            phone.assignContactFromPhone("1234567890", true, bundle);
        } else{
            phone.assignContactFromPhone("1234567890", true);
        }

    }
}