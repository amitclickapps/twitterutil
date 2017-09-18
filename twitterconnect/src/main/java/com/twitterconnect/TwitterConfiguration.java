package com.twitterconnect;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.twitter.sdk.android.core.DefaultLogger;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterConfig;

import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by clickapps on 18/9/17.
 */

public class TwitterConfiguration {
    private static HttpLoggingInterceptor loggingInterceptor;

    private TwitterConfiguration() {
    }

    public static HttpLoggingInterceptor getLoggingInterceptor() {
        return loggingInterceptor;
    }

    public static class Builder {
        private Context context;
        private String consumerKey, secretKey;
        private boolean isDebug;
        private HttpLoggingInterceptor loggingInterceptor;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder keys(@NonNull String consumerKey, @NonNull String secretKey) {
            this.consumerKey = consumerKey;
            this.secretKey = secretKey;
            return this;
        }

        public Builder isDebug(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        public void config() {
            final HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            TwitterConfig.Builder builder = new TwitterConfig.Builder(context);
            builder.debug(isDebug);
            if (!isDebug) {
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
                builder.logger(new DefaultLogger(Log.DEBUG));
            }
            this.loggingInterceptor = loggingInterceptor;
            builder.twitterAuthConfig(new TwitterAuthConfig(consumerKey, secretKey));
            Twitter.initialize(builder.build());
            TwitterConfiguration.loggingInterceptor = loggingInterceptor;

        }

    }
}
