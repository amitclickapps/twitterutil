package com.twitterconnect.controller;

import com.twitter.sdk.android.core.models.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by amit on 10/2/17.
 */

public interface TwitterService {

    @GET(WebEndPoint.PROFILE)
    Call<User> show(@QueryMap Map<String, String> map);
}
