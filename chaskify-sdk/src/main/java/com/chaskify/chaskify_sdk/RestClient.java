package com.chaskify.chaskify_sdk;

import com.chaskify.chaskify_sdk.rest.converter.LenientGsonConverterFactory;
import com.chaskify.chaskify_sdk.rest.converter.ToStringConverterFactory;
import com.chaskify.chaskify_sdk.rest.model.BaseResponse;
import com.chaskify.chaskify_sdk.rest.model.login.Credentials;
import com.chaskify.chaskify_sdk.rest.model.login.LoginResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class RestClient<T> {

    public static final String BASE = "http://customer.chaskify.com/api/";

    protected T mApi;

    private static final int CONNECTION_TIMEOUT_MS = 3000;
    private static final int READ_TIMEOUT_MS = 6000;
    private static final int WRITE_TIMEOUT_MS = 6000;

    protected Credentials mCredentials;

    protected HomeServerConnectionConfig mHsConfig;

    protected OkHttpClient mOkHttpClient;

    public RestClient(HomeServerConnectionConfig hsConfig, Class<T> type) {
        this.mHsConfig = hsConfig;
        this.mCredentials = hsConfig.getCredentials();

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
                .addConverterFactory(new ToStringConverterFactory())
                .baseUrl(BASE)
                .build();

        mApi = retrofit.create(type);
    }

    protected Gson getGson() {

        return new Gson();
    }

}
