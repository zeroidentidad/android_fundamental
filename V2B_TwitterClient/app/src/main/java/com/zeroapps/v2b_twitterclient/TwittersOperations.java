package com.zeroapps.v2b_twitterclient;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class TwittersOperations {
    private static Twitter twitter;
    private static final String TEMATICA="golang";

    private static Twitter getTwitterInstance(){
        if (twitter==null){
            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true).setOAuthConsumerKey("***").setOAuthConsumerSecret("***").setOAuthAccessToken("***").setOAuthAccessTokenSecret("***");
            TwitterFactory tf = new TwitterFactory(cb.build());
            twitter = tf.getInstance();
        }

        return twitter;
    }

    public static QueryResult getTweets(){
        Query query =  new Query(TEMATICA);
        try {
            QueryResult result = getTwitterInstance().search(query);
            return result;
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }
}
