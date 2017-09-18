package com.twitterconnect.controller;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitterconnect.Param;
import com.twitterconnect.TwitterConfiguration;
import com.twitterconnect.TwitterConnect;

import okhttp3.OkHttpClient;

/**
 * Created by amit on 10/2/17.
 */

public class LoginController {

    /**
     * @param param set {@linkplain Activity} Param
     */
    public void login(@NonNull final Param param) {
        TwitterConnect.get().getTwitterAuthClient().authorize(param.getContext(), new Callback<TwitterSession>() {

            @Override
            public void success(Result<TwitterSession> twitterSessionResult) {
                // Success
                final TwitterSession activeSession = TwitterConnect.get().session();
                final OkHttpClient customClient = new OkHttpClient.Builder()
                        .addInterceptor(TwitterConfiguration.getLoggingInterceptor()).build();
                TwitterApiClient customApiClient = new TwClient(activeSession, customClient);
                TwitterCore.getInstance().addApiClient(activeSession, customApiClient);
                if (param.getCallback() != null)
                    param.getCallback().onSuccess(twitterSessionResult.data);
            }

            @Override
            public void failure(TwitterException e) {
                if (param.getCallback() != null)
                    param.getCallback().onError(e);
            }
        });
    }

    /**
     * @param param set {@linkplain Activity} Param
     */
    public void email(@NonNull final Param param) {
        TwitterSession session = TwitterCore.getInstance().getSessionManager().getActiveSession();
        TwitterConnect.get().getTwitterAuthClient().requestEmail(session, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                // Do something with the result, which provides the email address
                if (param.getCallback() != null)
                    param.getCallback().onSuccess(result.data);
            }

            @Override
            public void failure(TwitterException exception) {
                // Do something on failure
                if (param.getCallback() != null)
                    param.getCallback().onError(exception);
            }
        });
    }

    public void logout(@NonNull Param param) {
        TwitterCore.getInstance().getSessionManager().clearActiveSession();
    }
}
