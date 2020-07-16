package com.example.googlemapsexample.twitter;

import twitter4j.GeoLocation;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterOperations {

	private static Twitter twitter;
	private static final String TEMATICA = "longitude";

	private static Twitter getTwitterInstance() {
		if ( twitter == null) {
			ConfigurationBuilder cb = new ConfigurationBuilder();
			cb.setDebugEnabled(true)
			.setOAuthConsumerKey("-")
			.setOAuthConsumerSecret("-")
			.setOAuthAccessToken("-")
			.setOAuthAccessTokenSecret("-");
			TwitterFactory tf = new TwitterFactory(cb.build());
			twitter = tf.getInstance();
		}
		return twitter;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static QueryResult getTweets() {
		Query query = new Query(TEMATICA);
		query.setCount(200);
		QueryResult result = null;
		try {
			result = getTwitterInstance().search(query);
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
