package com.twitterconnect.controller;

import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterSession;

import okhttp3.OkHttpClient;

/**
 * Created by amit on 10/2/17.
 */

public class TwClient extends TwitterApiClient {

    public TwClient(OkHttpClient client) {
        super(client);
    }

    public TwClient(TwitterSession session, OkHttpClient client) {
        super(session, client);
    }

    /**
     * Provide CustomService with defined endpoints
     */
    public TwitterService getTwitterService() {
        return getService(TwitterService.class);
    }
}
