package com.codecool.mydemoapp.messaging;

import android.util.Log;

import com.codecool.mydemoapp.util.Utility;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = ">>>>> ";
    private static final String SERVER_URL = "http://192.168.161.132:8080/";

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.i(TAG, "Refreshed token: " + token);

        sendTokenToServer(token);
    }

    private void sendTokenToServer(String token) {
        OkHttpClient client = new OkHttpClient();

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, "{\"token\": \"" + token + "\"}");

        Request request = new Request.Builder()
                .url(SERVER_URL)
                .post(body)
                .build();

        Log.i(TAG, "RequestBody sent: " + Utility.requestToString(request));

        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
