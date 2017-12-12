package com.chaskify.chaskify_sdk;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.chaskify.chaskify_sdk.rest.converter.ToStringConverterFactory;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestClient<T> {

    public static final String BASE = "http://customer.chaskify.com/api/";

    protected T mApi;

    // the user agent
    private static String sUserAgent = null;

    private static final int CONNECTION_TIMEOUT_MS = 3000;
    private static final int READ_TIMEOUT_MS = 6000;
    private static final int WRITE_TIMEOUT_MS = 6000;

    protected Credentials mCredentials;

    protected OkHttpClient mOkHttpClient;

    public RestClient(Credentials credentials, Class<T> type) {
        this.mCredentials = credentials;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        mOkHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(logging)
                .connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(new ToStringConverterFactory())
                .baseUrl(BASE)
                .build();

        mApi = retrofit.create(type);
    }

    /**
     * Create an user agent with the application version.
     *
     * @param appContext the application context
     */
    public static void initUserAgent(Context appContext) {
        String appName = "";
        String appVersion = "";

        if (null != appContext) {
            try {
                PackageManager pm = appContext.getPackageManager();
                ApplicationInfo appInfo = pm.getApplicationInfo(appContext.getApplicationContext().getPackageName(), 0);
                appName = pm.getApplicationLabel(appInfo).toString();

                PackageInfo pkgInfo = pm.getPackageInfo(appContext.getApplicationContext().getPackageName(), 0);
                appVersion = pkgInfo.versionName;
            } catch (Exception e) {
                //Log.e(LOG_TAG, "## initUserAgent() : failed " + e.getMessage());
            }
        }

        sUserAgent = System.getProperty("http.agent");

        // cannot retrieve the application version
        if (TextUtils.isEmpty(appName) || TextUtils.isEmpty(appVersion)) {
            if (null == sUserAgent) {
                sUserAgent = "Java" + System.getProperty("java.version");
            }
            return;
        }

        // if there is no user agent or cannot parse it
        if ((null == sUserAgent) || (sUserAgent.lastIndexOf(")") == -1) || (sUserAgent.indexOf("(") == -1)) {
            sUserAgent = appName + "/" + appVersion + " ( MatrixAndroidSDK " + BuildConfig.VERSION_NAME + ")";
        } else {
            // update
            sUserAgent = appName + "/" + appVersion + " " +
                    sUserAgent.substring(sUserAgent.indexOf("("), sUserAgent.lastIndexOf(")") - 1) +
                    "; MatrixAndroidSDK " + BuildConfig.VERSION_NAME + ")";
        }
    }



    protected Gson getGson() {
        return new Gson();
    }

}
