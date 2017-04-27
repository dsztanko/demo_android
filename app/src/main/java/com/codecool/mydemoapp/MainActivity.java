package com.codecool.mydemoapp;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
    private static String TAG = ">>>>> " + MainActivity.class.getName();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void onClickNotification(View view) {
        Log.i(TAG, R.string.notify_me + " clicked");

        EditText et1 = (EditText) findViewById(R.id.editText1);
        EditText et2 = (EditText) findViewById(R.id.editText2);

        String title = et1.getText().toString().trim();
        String body = et2.getText().toString().trim();

        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify = new NotificationCompat.Builder
                (this)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.drawable.notif_icon)
                .build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notify);
        Log.i(TAG, "User has been notified.");
    }
}
