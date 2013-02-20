package com.thinkingbridge.welcome;

import java.lang.reflect.Method;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;


public class BootCompleteReceiver extends BroadcastReceiver {

    private static final String CHANGELOG_INTENT = "tb.welcome.ChangeLogActivity";
    private static final String WELCOME_INTENT = "tb.welcome.MainActivity";
    private static final String TAG = "WelcomeBootReceiver";
    int show;
    String a;

    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity aboutActivity = new MainActivity();
        Log.e(TAG, "Started");

        SharedPreferences prefs = context.getSharedPreferences(MainActivity.PREFS_NAME, 0);
        show = prefs.getInt(MainActivity.SHOW, 0);
        String previousRomVersion = prefs.getString(MainActivity.ROM_VERSION, "0.0.0");
        String currentRomVersion = aboutActivity.getRomVersion();
        Log.e(TAG, "Previous ROM Version: " + previousRomVersion);
        Log.e(TAG, "Current ROM Version: " + currentRomVersion);
        Log.e(TAG,"show");
        if(show == 1){
            if (!currentRomVersion.equals(previousRomVersion)) {
                //if (currentRomVersion.equals(previousRomVersion)) { //DEBUGGING
                    Log.e(TAG, "Running ChangeLog Activity");

                    Intent i = new Intent();
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setClassName(context, CHANGELOG_INTENT);
                    context.startActivity(i);
                } 
        }
        if(show == 0){
            Log.e(TAG, "Running Welcome Activity");
            Intent i = new Intent();
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setClassName(context, WELCOME_INTENT);
            context.startActivity(i);
        }
    }
}
