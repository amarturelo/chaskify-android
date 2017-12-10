package com.chaskify.android;

/**
 * Created by alberto on 9/12/17.
 */

public class Chaskify {
    private static final Chaskify ourInstance = new Chaskify();

    public static Chaskify getInstance() {
        return ourInstance;
    }

    private Chaskify() {
    }
}
