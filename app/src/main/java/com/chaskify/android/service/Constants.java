package com.chaskify.android.service;

public class Constants {
    public interface ACTION {
        public static String MAIN_ACTION = Constants.class.getPackage().toString() + ".action.attach";
        public static String STARTFOREGROUND_ACTION = Constants.class.getPackage().toString() + ".action.startforeground";
        public static String STOPFOREGROUND_ACTION = Constants.class.getPackage().toString() + ".action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        public static int FOREGROUND_SERVICE = 101;
    }
}