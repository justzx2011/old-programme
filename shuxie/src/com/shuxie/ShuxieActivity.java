package com.shuxie;

import com.sqlite.*;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebSettings.PluginState;
import android.widget.Button;
import android.widget.TextView;

public class ShuxieActivity extends Activity {
    /** Called when the activity is first created. */
	
	private TextView  query;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
          WebView wv = (WebView)findViewById(R.id.webViewShuXie);
          String url = "file:///mnt/sdcard/shuxie/show.html";
          System.out.println(url);
          wv.getSettings().setPluginsEnabled(true);
          wv.getSettings().setPluginState(PluginState.ON);
          wv.getSettings().setAllowFileAccess(true);
          wv.getSettings().setJavaScriptEnabled(true);
          wv.loadUrl(url);
          query= (TextView)findViewById(R.id.textView1);
          query.setOnClickListener(new QueryListener());
    }
    class QueryListener implements OnClickListener{

		public void onClick(View v) {
			System.out.println("aaa------------------");
			Log.d("myDebug", "myFirstDebugMsg");
			
			DatabaseHelper dbHelper = new DatabaseHelper(ShuxieActivity.this,"hz.db");
			SQLiteDatabase db = dbHelper.getReadableDatabase();
			//String select = "Select hz_value, hz_order, hz_lh, hz_mh from hz_info Where hz_id ==1 ";  
			Cursor cursor = db.query("hz_info", new String[] {"hz_value", "hz_order", "hz_lh", "hz_mh"}, 
	                "hz_id = 1" , null, null, null, null);
			while(cursor.moveToNext()){
				String order = cursor.getString(cursor.getColumnIndex("hz_order"));
				String lh = cursor.getString(cursor.getColumnIndex("hz_lh"));
				String mh = cursor.getString(cursor.getColumnIndex("hz_mh"));
				String data= order + "###" + lh + mh +"******";
				System.out.println("query--->" + data);
			}
		}
    }
}