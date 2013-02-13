package com.thinkingbridge.welcome;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ResolveInfo;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.SystemProperties;

public class MainActivity extends Activity {
    BufferedReader inputStream;
    String value;
    ArrayList<Drawable>myArr;
    int page = 1;
    public static final String ROM_VERSION = "rom_version";
    public static final String PREFS_NAME = "Welcome";
    public static final String SHOW = "show";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        checkpage();		
		prepare Prepare = new prepare();
		Prepare.start();
        SharedPreferences prefs = this.getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString(ROM_VERSION, getRomVersion());
        prefEditor.putInt(SHOW, 1);
        prefEditor.commit();
	}
	public void button(View v){
		page = page+1;
		checkpage();
	}
	public void checkpage(){
		if(page == 1){
			setContentView(R.layout.activity_1);
		}else if(page == 2){
			setContentView(R.layout.activity_2);
			TextView changelog = (TextView) findViewById(R.id.changelog);
			TextView buildnumber = (TextView) findViewById(R.id.buildnumber);
	        value = getRomVersion();
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
		}else if(page == 3){			
			 setContentView(R.layout.activity_3);
			  ImageView app1 = (ImageView) findViewById(R.id.imageView1);
			  ImageView app2 = (ImageView) findViewById(R.id.imageView2);
			  ImageView app3 = (ImageView) findViewById(R.id.imageView3);
			  ImageView app4 = (ImageView) findViewById(R.id.imageView4);
			  ImageView app5 = (ImageView) findViewById(R.id.imageView5);
			  ImageView app6 = (ImageView) findViewById(R.id.imageView6);
			  app1.setImageDrawable(myArr.get(0));
			  app2.setImageDrawable(myArr.get(1));
			  app3.setImageDrawable(myArr.get(2));
			  app4.setImageDrawable(myArr.get(3));
			  app5.setImageDrawable(myArr.get(4));
			  app6.setImageDrawable(myArr.get(5));
		}else if(page == 4){
			setContentView(R.layout.activity_4);
		}else if(page == 5){
			setContentView(R.layout.activity_5);	
		}else if(page == 6){
			setContentView(R.layout.activity_6);
		}		
	}
	class prepare extends Thread{
		public void run(){
			Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
	        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
			myArr= new ArrayList<Drawable>();
	        final List<ResolveInfo> pkgAppsList = getPackageManager().queryIntentActivities( mainIntent, 0);
	        for (int i=0; i<pkgAppsList.size(); i++) {
	            ResolveInfo ai = pkgAppsList.get(i);
	            // On a user build, we only allow debugging of apps that
	            // are marked as debuggable.  Otherwise (for platform development)
	            // we allow all apps.
	            ResolveInfo info = new ResolveInfo();
	            info = ai;
	            myArr.add(info.loadIcon(getPackageManager()));
	        }
	      Collections.shuffle(myArr);
			
		};
	}
    public String getRomVersion() {
        String version = SystemProperties.get("ro.tb.version");
        return version;
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	public void skip(View v){
		Intent i = new Intent(MainActivity.this, Piehelp.class);
		startActivity(i);
		finish();
	}
}
	 