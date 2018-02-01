package com.chaskify.android.rx;

import android.graphics.Bitmap;

import com.chaskify.chaskify_sdk.crypto.Base64;

import java.io.ByteArrayOutputStream;

import io.reactivex.Single;

/**
 * Created by alberto on 1/02/18.
 */

public class RxImageToBase64 {
    public static Single<String> toBase64(Bitmap bitmap) {
        return Single.create(e -> {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            e.onSuccess(Base64.encodeBytes(byteArray, Base64.NO_OPTIONS));
        });
    }
}
