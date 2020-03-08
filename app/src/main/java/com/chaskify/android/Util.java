package com.chaskify.android;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by alberto on 14/12/17.
 */

public class Util {
    private static final SimpleDateFormat dateFormatActionBar = new SimpleDateFormat("MMM,d", /*Locale.getDefault()*/Locale.getDefault());
    private static final SimpleDateFormat dateFormatRequest = new SimpleDateFormat("yyyy-MM-dd", /*Locale.getDefault()*/Locale.getDefault());

    public static String timeZone() {
        return String.valueOf(new Date().getTimezoneOffset());
    }



    public static String chaskifyFormatDateRequest(Date date) {
        return dateFormatRequest.format(date);
    }
}
