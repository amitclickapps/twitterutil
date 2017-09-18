package com.twitterconnect.callback;

/**
 * A fbconnect.callback class for the Facebook SDK.
 */
public interface OnTwitterCallback<RESULT, ERROR> {

    public void onSuccess(RESULT result);

    public void onError(ERROR error);
}
