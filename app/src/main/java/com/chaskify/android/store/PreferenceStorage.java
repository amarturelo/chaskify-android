package com.chaskify.android.store;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Path;

import com.annimon.stream.Optional;

/**
 * Created by alberto on 14/12/17.
 */

public class PreferenceStorage {
    private Context mContext;

    private static final String PREFS_LOGIN = "Chaskify.LoginStorage";

    SharedPreferences prefs = null;
    SharedPreferences.Editor editor = null;
    private String PREFS_KEY_DEFAULT = "KEY_SESSION_DEFAULT";

    public PreferenceStorage(Context mContext) {
        this.mContext = mContext;
        prefs = mContext.getSharedPreferences(PREFS_LOGIN, Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public Optional<String> getDefault() {
        return prefs.getString(PREFS_KEY_DEFAULT, "").isEmpty()?Optional.empty(): Optional.of(prefs.getString(PREFS_KEY_DEFAULT, ""));
    }

    public void setDefault(String username) {
        editor.putString(PREFS_KEY_DEFAULT, username);
        editor.apply();
    }
}
