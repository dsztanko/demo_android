package com.codecool.mydemoapp.util;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Request;
import okio.Buffer;

public class Utility {

    @NonNull
    public static String requestToString(Request request) {
        Buffer buffer = new Buffer();
        try {
            request.body().writeTo(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.readUtf8();
    }
}
