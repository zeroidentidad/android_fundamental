package com.example.googlemapsexample;

import java.util.ArrayList;

import twitter4j.QueryResult;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;

import com.bugsense.trace.BugSenseHandler;
import com.example.googlemapsexample.datamodel.Tweet;
import com.example.googlemapsexample.twitter.TwitterOperations;
import com.flurry.android.FlurryAgent;
import com.google.analytics.tracking.android.EasyTracker;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends Activity {

	private GoogleMap        map = null;
	private ArrayList<Tweet> _myArrayList;
	private QueryResult      _myQueryResult;
	private ProgressDialog   _dialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		BugSenseHandler.initAndStartSession(this, "MI API KEY");
		setContentView(R.layout.activity_main);
		getGoogleMap();
		this._myArrayList = new ArrayList<Tweet>();
		_dialog = new ProgressDialog( this );
		_dialog.setMessage(getResources().getString((R.string.loading_screen)));
		new DownloadTweets().execute((Void)null);
	}

	
	
	@Override
	protected void onStart() {
		super.onStart();
		EasyTracker.getInstance().activityStart(this);
		FlurryAgent.onStartSession(this, "API KEY");
	}



	@Override
	protected void onStop() {
		super.onStop();
		EasyTracker.getInstance().activityStop(this);
		BugSenseHandler.closeSession(this);
		FlurryAgent.onEndSession(this);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private GoogleMap getGoogleMap() {
		if ( map == null ) {
			map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		}
		return map;
	}

	public ArrayList<Tweet> getMyArrayList() {
		return _myArrayList;
	}

	public void setMyArrayList(ArrayList<Tweet> _myArrayList) {
		this._myArrayList = _myArrayList;
	}

	public QueryResult getMyQueryResult() {
		return _myQueryResult;
	}

	public void setMyQueryResult(QueryResult _myQueryResult) {
		this._myQueryResult = _myQueryResult;
	}
	
	
	private class DownloadTweets extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			_myQueryResult = TwitterOperations.getTweets();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
			
			for ( twitter4j.Status tweet : _myQueryResult.getTweets()) {
				Tweet twitter = new Tweet();
				twitter.set_nombre(tweet.getUser().getName());
				twitter.set_contenido(tweet.getText());
				if (tweet.getGeoLocation() != null) {
					twitter.set_latitude(tweet.getGeoLocation().getLatitude());
					twitter.set_latitude(tweet.getGeoLocation().getLongitude());
				
				
				Marker myMarker  = getGoogleMap().addMarker( new MarkerOptions()
				.title(twitter.get_nombre())
				.title(twitter.get_contenido())
				.position(new LatLng(twitter.get_latitude(),twitter.get_longitude() ) ));
				_myArrayList.add(twitter);
				}
			}
			_dialog.hide();
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			_dialog.show();
		}
		
		
	}
	
	
}
