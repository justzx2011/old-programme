package com.GyEmachine;

import com.index.index;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class GyEmachineActivity extends Activity {
    /** Called when the activity is first created. */
	private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        public void run() {    
            //handler.postDelayed(this, 1000 * 2);// 间隔2秒
        	//刷新msg的内容
        	Intent intent = new Intent();
     	    intent.setClass(GyEmachineActivity.this, index.class);	        	
     	    startActivity(intent);
     	    GyEmachineActivity.this.finish();
        }
    }; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        handler.postDelayed(runnable, 1000*4);
    }

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
    
    
}