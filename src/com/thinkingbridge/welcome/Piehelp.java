package com.thinkingbridge.welcome;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Piehelp extends Activity {
	Dialog piehelp;
	 ImageView finger;
	 int move1 = 0;
	public void onCreate(Bundle savedInstance){
          super.onCreate(savedInstance);
             setContentView(R.layout.piecontrol);
        	 finger = (ImageView)findViewById(R.id.finger);
             Move move = new Move();
             move.start();
             TextView a = (TextView)findViewById(R.id.textView4);
             a.setVisibility(View.INVISIBLE);
         	 getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


	}
	public void finish(View v){
		finish();
	}
	class Move extends Thread {
		public void run(){
        	 finger.scrollBy(-40, -320);
			for(;;){
				move1 = move1+1;
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(move1 == 80){
		        	 runOnUiThread(new Runnable(){
		        		 public void run(){
		                 	 finger.scrollBy(0, -80);
		        		 }
		        	 });
		        	 move1 = 0;
				}else{
		        	 runOnUiThread(new Runnable(){
		        		 public void run(){
		                 	 finger.scrollBy(0, 1);
		        		 }
		        	 });
				}
		}
		}
	}

}
