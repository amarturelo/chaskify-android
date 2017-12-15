package com.chaskify.android;

import java.util.Date;

/**
 * Created by alberto on 14/12/17.
 */

public class Util {
    public static String timeZone() {
        return String.valueOf(new Date().getTimezoneOffset());
    }
}
