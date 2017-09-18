package com.twitterconnect.controller;

import android.support.annotation.NonNull;

import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterApiClient;
import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import com.twitterconnect.Param;
import com.twitterconnect.TwitterConfiguration;
import com.twitterconnect.TwitterConnect;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by amit on 10/2/17.
 */

public class ProfileController {

    public void profile(@NonNull final Param param) {
        final TwitterSession activeSession = TwitterConnect.get().session();
        // example of custom OkHttpClient with logging HttpLoggingInterceptor

        final OkHttpClient customClient = new OkHttpClient.Builder()
                .addInterceptor(TwitterConfiguration.getLoggingInterceptor()).build();
        // pass custom OkHttpClient into TwitterApiClient and add to TwitterCore
        final TwitterApiClient customApiClient;
        if (activeSession != null) {
            customApiClient = new TwClient(activeSession, customClient);
            TwitterCore.getInstance().addApiClient(activeSession, customApiClient);
            TwClient twitterApiClient = (TwClient) TwitterCore.getInstance().getApiClient();
            TwitterService statusesService = twitterApiClient.getTwitterService();
            Map<String, String> map = new LinkedHashMap<>();
            map.put(WebEndPoint.USER_ID_KEY, "" + activeSession.getUserId());
            statusesService.show(map).enqueue(new TwCallback<User>() {
                @Override
                public void success(Result<User> result) {
                    if (param.getCallback() != null) {
                        param.getCallback().onSuccess(result.data);
                    }
                }

                @Override
                public void failure(TwitterException exception) {
                    if (param.getCallback() != null) {
                        param.getCallback().onError(exception);
                    }
                }
            });
        }
    }
}
