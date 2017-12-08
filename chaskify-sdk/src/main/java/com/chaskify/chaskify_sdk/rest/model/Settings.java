package com.chaskify.chaskify_sdk.rest.model;

import java.util.HashMap;

/**
 * Created by alberto on 7/12/17.
 */

public class Settings {
    /*"enabled_push": "1",
            "language": {
        "en": "English"
    }*/

    private String enabled_push;
    private HashMap<String, String> language;

    public Settings() {
    }

    public String getEnabled_push() {
        return enabled_push;
    }

    public Settings setEnabled_push(String enabled_push) {
        this.enabled_push = enabled_push;
        return this;
    }

    public HashMap<String, String> getLanguage() {
        return language;
    }

    public Settings setLanguage(HashMap<String, String> language) {
        this.language = language;
        return this;
    }
}
