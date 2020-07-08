package com.zeroapps.v2b_twitterclient;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QueryResult result = TwittersOperations.getTweets();
        ListView listView = (ListView) findViewById(R.id.listViewTweets);
        ArrayList<Tweet> array = new ArrayList<Tweet>();
        for (Status tweets: result.getTweets()){
            Tweet tweet = new Tweet();
            tweet.setNombre(tweets.getUser().getName());
            tweet.setContenido(tweets.getText());
            array.add(tweet);
        }
        listView.setAdapter(new TwitterAdapter(this, R.layout.row, array));
    }

    private class TwitterAdapter extends ArrayAdapter<Tweet>{
        private ArrayList<Tweet> items;
        public TwitterAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Tweet> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View v = convertView;
            if (v==null){
                LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v=vi.inflate(R.layout.row, null);
            }
            Tweet tweet = items.get(position);
            if (tweet != null) {
                TextView textNombre = (TextView) v.findViewById(R.id.textNombre);
                TextView textContenido = (TextView) v.findViewById(R.id.textContenido);

                textNombre.setText(tweet.getNombre());
                textContenido.setText(tweet.getContenido());
            }
            return super.getView(position, convertView, parent);
        }
    }
}