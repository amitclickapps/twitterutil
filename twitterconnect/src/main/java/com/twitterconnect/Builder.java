package com.twitterconnect;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.twitterconnect.callback.OnTwitterCallback;
import com.twitterconnect.controller.LoginController;
import com.twitterconnect.controller.ProfileController;

import java.util.Map;

/**
 * Created by amit on 10/2/17.
 */

public class Builder {

    private Param param;

    /**
     * Instantiates a new Builder.
     *
     * @param context the context
     */
    public Builder(@NonNull Activity context, @NonNull Param.TWAction twAction) {
        param = new Param();
        param.context = context;
        param.twAction = twAction;
    }

    public <RESULT, ERROR> Builder callback(@NonNull OnTwitterCallback<RESULT, ERROR> callback) {
        param.callback = callback;
        return this;
    }

    public Builder requestMap(@NonNull Map<String, String> map) {
        param.requestMap = map;
        return this;
    }

    public void build() {
        switch (param.twAction) {
            case LOGIN:
                new LoginController().login(param);
                break;
            case LOGOUT:
                new LoginController().logout(param);
                break;
            case EMAIL:
                new LoginController().email(param);
                break;
            case PROFILE:
                new ProfileController().profile(param);
                break;
            default:
                break;
        }
    }
}
