package com.twitterconnect.controller;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;

/**
 * Created by amit on 13/2/17.
 */

public class TwCallback<T> extends Callback<T> {

    public TwCallback() {

    }

    @Override
    public void success(Result<T> result) {

    }

    @Override
    public void failure(TwitterException exception) {

    }
}
