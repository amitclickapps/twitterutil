package com.twitterconnect.controller;

/**
 * Created by amit on 4/2/17.
 */

public class WebEndPoint {
    public static final String USER_ID_KEY = "user_id";
    private static final String BASE_URL = "https://api.twitter.com/";
    protected static final String VERSION = "1.1/";

    protected static final String PROFILE = "/" + VERSION + "users/show.json";
    protected static final String FRIENDS = "/" + VERSION + "friends/list.json";

    protected static int PROFILE_TASK_ID = 1;
}
