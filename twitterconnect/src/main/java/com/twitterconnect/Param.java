package com.twitterconnect;

import android.app.Activity;

import com.twitterconnect.callback.OnTwitterCallback;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by amit on 10/2/17.
 */

public class Param {

    protected Activity context;

    protected TWAction twAction;

    protected OnTwitterCallback callback;

    protected Map<String, String> requestMap = new LinkedHashMap<>();

    public enum TWAction {
        /**
         * TwitterSession
         **/
        LOGIN,
        /**
         * String
         **/
        EMAIL,
        /**
         * No Model
         **/
        LOGOUT,

        /**
         * User
         **/
        PROFILE,
    }

    public Activity getContext() {
        return context;
    }

    public TWAction getTwAction() {
        return twAction;
    }

    public OnTwitterCallback getCallback() {
        return callback;
    }

    public Map<String, String> getRequestMap() {
        return requestMap;
    }
}
