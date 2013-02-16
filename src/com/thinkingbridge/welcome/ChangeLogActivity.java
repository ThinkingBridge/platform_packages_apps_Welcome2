package com.thinkingbridge.welcome;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources.NotFoundException;
import android.os.Bundle;
import android.os.SystemProperties;
import android.view.View;
import android.widget.TextView;

public class ChangeLogActivity extends Activity {
	BufferedReader inputStream;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.changelog);
        MainActivity aboutActivity = new MainActivity();
        SharedPreferences prefs = this.getSharedPreferences(MainActivity.PREFS_NAME, 0);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString(MainActivity.ROM_VERSION, getRomVersion());
        prefEditor.commit();
		TextView changelog = (TextView) findViewById(R.id.changelog);
		TextView buildnumber = (TextView) findViewById(R.id.buildnumber);
        String value = getRomVersion();
        if(value.trim().equals("")){
        	value = getString(R.string.cversion)+getString(R.string.unknown);
        }
        try {
			inputStream = new BufferedReader(
			        new InputStreamReader(getResources().openRawResource(R.raw.changelog),"UTF-8"),512);
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int i;
            try {
                i = inputStream.read();
            while (i != -1) {
            	
               byteArrayOutputStream.write(i);
               i = inputStream.read();
            }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        buildnumber.setText(getString(R.string.cversion)+value);
        changelog.setText(byteArrayOutputStream.toString());
	}
    public String getRomVersion() {
        String version = SystemProperties.get("ro.tb.version");
        return version;
    }
	public void skip(View v){
		finish();
	}

}
