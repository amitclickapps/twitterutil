package com.twitterconnect.callback;

import com.twitter.sdk.android.core.TwitterException;

/**
 * Created by amit on 4/2/17.
 */

public class TwException extends com.twitter.sdk.android.core.TwitterException {
    public TwException(String detailMessage) {
        super(detailMessage);
    }

    public TwException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }
}
