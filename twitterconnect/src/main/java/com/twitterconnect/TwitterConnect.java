package com.twitterconnect;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.twitter.sdk.android.core.TwitterCore;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

/**
 * Created by amit on 10/2/17.
 */

public class TwitterConnect {

    private static volatile TwitterConnect sTwitterConnect;
    private static volatile TwitterAuthClient sTwitterAuthClient;

    private TwitterConnect() {

    }

    /**
     * Get web connect.
     *
     * @return the web connect
     */
    public static TwitterConnect get() {
        if (sTwitterConnect == null) {
            synchronized (TwitterConnect.class) {
                if (sTwitterConnect == null) {
                    sTwitterConnect = new TwitterConnect();
                }
            }
        }
        return sTwitterConnect;
    }

    public TwitterAuthClient getTwitterAuthClient() {
        if (sTwitterAuthClient == null) {
            synchronized (TwitterConnect.class) {
                if (sTwitterAuthClient == null) {
                    sTwitterAuthClient = new TwitterAuthClient();
                }
            }
        }
        return sTwitterAuthClient;
    }

    public
    @Nullable
    TwitterSession session() {
        return TwitterCore.getInstance()
                .getSessionManager().getActiveSession();
    }

    /**
     * onActivityResult in your Activity, pass the result to the CallbackManager:
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        if (sTwitterAuthClient != null)
            sTwitterAuthClient.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * With builder.
     *
     * @param context the context
     * @return the builder
     */
    public static Builder with(@NonNull Activity context, @NonNull Param.TWAction twAction) {
        return new Builder(context, twAction);

    }
}
