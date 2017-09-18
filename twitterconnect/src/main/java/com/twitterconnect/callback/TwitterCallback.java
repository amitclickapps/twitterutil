package com.twitterconnect.callback;

/**
 * Created by amit on 4/2/17.
 */

public abstract class TwitterCallback<RESULT, ERROR> implements OnTwitterCallback<RESULT, ERROR> {

    @Override
    public void onSuccess(RESULT result) {

    }

    @Override
    public void onError(ERROR error) {

    }
}
