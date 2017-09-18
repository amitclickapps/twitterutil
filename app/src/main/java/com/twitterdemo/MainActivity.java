package com.twitterdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.TextView;

import com.twitter.sdk.android.core.TwitterAuthToken;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.models.User;
import com.twitterconnect.Param;
import com.twitterconnect.TwitterConfiguration;
import com.twitterconnect.TwitterConnect;
import com.twitterconnect.callback.OnTwitterCallback;


/**
 * Created by amit on 9/2/17.
 */

public class MainActivity extends FragmentActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "mpUVvDLh4EE0376IdQZfGI5vf";
    private static final String TWITTER_SECRET = "L8SEa7dfP1qdCSUTfrkXrI0CjY4uqNUin7tfVc3gn588CUQomm";
    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        textView = (TextView) findViewById(android.R.id.text1);
        new TwitterConfiguration.Builder(this)
                .isDebug(true)
                .keys(TWITTER_KEY, TWITTER_SECRET)
                .config();
        if (TwitterConnect.get().session() == null) {
            TwitterConnect.with(this, Param.TWAction.LOGIN)
                    .callback(new OnTwitterCallback<TwitterSession, TwitterException>() {
                        @Override
                        public void onSuccess(TwitterSession twitterSessionResult) {
                            TwitterAuthToken authToken = twitterSessionResult.getAuthToken();
                            String token = authToken.token;
                            String secret = authToken.secret;
                            Log.i(getLocalClassName(), "UserName = " + twitterSessionResult.getUserName());
                            Log.i(getLocalClassName(), "Id = " + twitterSessionResult.getUserId());
                            Log.i(getLocalClassName(), "token = " + token);
                            Log.i(getLocalClassName(), "secret = " + secret);
                            email();
                        }

                        @Override
                        public void onError(TwitterException e) {
                            e.printStackTrace();
                        }
                    }).build();
        } else {
            email();
        }
    }

    public void email() {
        TwitterConnect.with(this, Param.TWAction.EMAIL)
                .callback(new OnTwitterCallback<String, TwitterException>() {
                    @Override
                    public void onSuccess(String s) {
                        Log.i(getLocalClassName(), "onSuccess = " + s);
                        profile();
                    }

                    @Override
                    public void onError(TwitterException e) {
                        e.printStackTrace();
                        profile();
                    }
                }).build();
    }

    public void profile() {

        TwitterConnect.with(this, Param.TWAction.PROFILE)
                .callback(new OnTwitterCallback<User, TwitterException>() {
                    @Override
                    public void onSuccess(User s) {
                        Log.i(getLocalClassName(), "Email = " + s.email);
                        Log.i(getLocalClassName(), "Name = " + s.name);
                        Log.i(getLocalClassName(), "profileImageUrl = " + s.profileImageUrl);
                        StringBuilder builder = new StringBuilder();
                        builder.append("Name = " + s.name + "\n");
                        builder.append("Email = " + s.email + "\n");
                        builder.append("Profile url = " + s.profileImageUrl + "\n");
                        builder.append("Follower Count = " + s.followersCount + "\n");
                        textView.setText(builder);
                    }

                    @Override
                    public void onError(TwitterException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TwitterConnect.get().onActivityResult(requestCode, resultCode, data);
    }
}
