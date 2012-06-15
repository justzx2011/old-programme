package com.cource36;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebSettings.PluginState;

public class Cource36Activity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        WebView wv = (WebView)findViewById(R.id.webView36);
//      String url ="file:///mnt/sdcard/video/b1.swf";
      //String url = "file://" + Environment.getExternalStorageDirectory().getPath() + "/" + "b1.swf";
      //System.out.println(url);
      wv.getSettings().setPluginsEnabled(true);
      wv.getSettings().setPluginState(PluginState.ON);
      wv.getSettings().setAllowFileAccess(true);
      wv.getSettings().setJavaScriptEnabled(true);
      wv.loadUrl("file:///mnt/sdcard/data/flash/course36/1/001.swf");
    }
    public void onBackPressed() {
		// TODO Auto-generated method stub
        Cource36Activity.this.finish();
		super.onBackPressed();
	}
}