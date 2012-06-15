package com.Smxx;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.DirectPy.DirectPy;
import com.DirectPy.R;

public class Smxx extends Activity {
    
	/*Gallery��Ҫ��ʾ��ͼƬ*/
	public   Integer[] myImageIds = 
	       {
			   R.drawable.b,
	           R.drawable.p,
	           R.drawable.m,
	           R.drawable.f,
	           R.drawable.d,
	           R.drawable.t,
	           R.drawable.n,
	           R.drawable.l,
	           R.drawable.g,
	           R.drawable.k,
	           R.drawable.h,
	           R.drawable.j,
	           R.drawable.q,
	           R.drawable.x,
	           R.drawable.zh,
	           R.drawable.ch,
	           R.drawable.sh,
	           R.drawable.r,
	           R.drawable.z,
	           R.drawable.c,
	           R.drawable.s,
	           R.drawable.y,
	           R.drawable.w,
	       }; 
	/*��ĸ��д����*/
	public  String[] myaudioIds = 
	       {
			"/sdcard/sound/b.wav",
			"/sdcard/sound/p.wav",
			"/sdcard/sound/m.wav",
			"/sdcard/sound/f.wav",
			"/sdcard/sound/d.wav",
			"/sdcard/sound/t.wav",
			"/sdcard/sound/n.wav",
			"/sdcard/sound/l.wav",
			"/sdcard/sound/g.wav",
			"/sdcard/sound/k.wav",
			"/sdcard/sound/h.wav",
			"/sdcard/sound/j.wav",
			"/sdcard/sound/q.wav",
			"/sdcard/sound/x.wav",
			"/sdcard/sound/zh.wav",
			"/sdcard/sound/ch.wav",
			"/sdcard/sound/sh.wav",
			"/sdcard/sound/r.wav",
			"/sdcard/sound/z.wav",
			"/sdcard/sound/c.wav",
			"/sdcard/sound/s.wav",
			"/sdcard/sound/y.wav",
			"/sdcard/sound/w.wav",
	       };
	/*����Ҫ��*/
    public  Integer[] myImagetextIds = 
	       {
			   R.drawable.a1b,
	           R.drawable.a1p,
	           R.drawable.a1m,
	           R.drawable.a1f,
	           R.drawable.a1d,
	           R.drawable.a1t,
	           R.drawable.a1n,
	           R.drawable.a1l,
	           R.drawable.a1g,
	           R.drawable.a1k,
	           R.drawable.a1h,
	           R.drawable.a1j,
	           R.drawable.a1q,
	           R.drawable.a1x,
	           R.drawable.a1zh,
	           R.drawable.a1ch,
	           R.drawable.a1sh,
	           R.drawable.a1r,
	           R.drawable.a1z,
	           R.drawable.a1c,
	           R.drawable.a1s,
	           R.drawable.a1y,
	           R.drawable.a1w,
	       }; 
	/*��ĸ��д����*/
	public  String[] myflashIds = 
	       {
			"file:///mnt/sdcard/video/flash/letter/b.swf",
			"file:///mnt/sdcard/video/flash/letter/p.swf",
			"file:///mnt/sdcard/video/flash/letter/m.swf",
			"file:///mnt/sdcard/video/flash/letter/f.swf",
			"file:///mnt/sdcard/video/flash/letter/d.swf",
			"file:///mnt/sdcard/video/flash/letter/t.swf",
			"file:///mnt/sdcard/video/flash/letter/n.swf",
			"file:///mnt/sdcard/video/flash/letter/l.swf",
			"file:///mnt/sdcard/video/flash/letter/g.swf",
			"file:///mnt/sdcard/video/flash/letter/k.swf",
			"file:///mnt/sdcard/video/flash/letter/h.swf",
			"file:///mnt/sdcard/video/flash/letter/j.swf",
			"file:///mnt/sdcard/video/flash/letter/q.swf",
			"file:///mnt/sdcard/video/flash/letter/x.swf",
			"file:///mnt/sdcard/video/flash/letter/zh.swf",
			"file:///mnt/sdcard/video/flash/letter/ch.swf",
			"file:///mnt/sdcard/video/flash/letter/sh.swf",
			"file:///mnt/sdcard/video/flash/letter/r.swf",
			"file:///mnt/sdcard/video/flash/letter/z.swf",
			"file:///mnt/sdcard/video/flash/letter/c.swf",
			"file:///mnt/sdcard/video/flash/letter/s.swf",
			"file:///mnt/sdcard/video/flash/letter/y.swf",
			"file:///mnt/sdcard/video/flash/letter/w.swf",
	       };
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.smxx);
	    //--------------------------------------����---------------------------------------
	   /*ͨ��findViewByIdȡ��*/
       Gallery g = (Gallery) findViewById(R.id.myGallerySmxx);
       /*���һ��ImageAdapter�����ø�Gallery����*/
       g.setAdapter(new ImageAdapter(this));
       
       /*���Ĭ�ϵ�ͼƬ*/
       
       /*����һ��itemclickListener��Toast������ͼƬ��λ��*/
       g.setOnItemClickListener(new OnItemClickListener() 
       {
    	   public void onItemClick(AdapterView<?> parent, View v, int position, long id) 
	         {
    			 ImageView img=(ImageView)findViewById(R.id.imgviewSmxx);
    			 WebView mWebFlash = (WebView) findViewById(R.id.webViewSmxx);
    			 WebSettings settings = mWebFlash.getSettings();
    			 final MediaPlayer mp=new MediaPlayer();
    		     img.setImageResource(myImagetextIds[position]);
    		     settings.setPluginsEnabled(true);
    		     settings.setJavaScriptEnabled(true);
    		     settings.setAllowFileAccess(true);
    		     mWebFlash.loadUrl(myflashIds[position]);
   				 try{
   			        	mp.setDataSource(myaudioIds[position]);		//������Ƶ������Initialized״̬��
   			        }catch(Exception e){e.printStackTrace();}
   			        try{
   			        	mp.prepare();							//����prepared״̬��
   			        }catch(Exception e){e.printStackTrace();}
   				 mp.start();										//��������
	         }
	       });
     }
     /*��дBaseAdapter�Զ���һImageAdapter class*/
     public class ImageAdapter extends BaseAdapter 
     {
       /*��������*/
       int mGalleryItemBackground;
       private Context mContext;
       
       /*ImageAdapter�Ĺ�����*/
       public ImageAdapter(Context c) 
       {
         mContext = c;
         /*ʹ����res/values/attrs.xml�е�<declare-styleable>����
         ��Gallery����*/
         TypedArray a = obtainStyledAttributes(R.styleable.Gallery);
         /*ȡ��Gallery���Ե�Index id*/
         mGalleryItemBackground = a.getResourceId(
             R.styleable.Gallery_android_galleryItemBackground, 0);
         /*�ö����styleable�����ܹ�����ʹ��*/ 
         a.recycle();
       }
       
       /*��д�ķ���getCount,����ͼƬ��Ŀ*/
       public int getCount() 
       {
         return myImageIds.length;
       }
       /*��д�ķ���getItemId,����ͼ�������id*/
       public Object getItem(int position) 
       {
         return position;
       } 
       public long getItemId(int position) 
       {
         return position;
       }
       /*��д�ķ���getView,����һView����*/
       public View getView(int position, View convertView, ViewGroup parent) 
       {
         /*����ImageView����*/
         ImageView i = new ImageView(mContext);
         /*����ͼƬ��imageView����*/
         i.setImageResource(myImageIds[position]);
         /*��������ͼƬ�Ŀ��*/
         i.setScaleType(ImageView.ScaleType.FIT_CENTER );
         /*��������Layout�Ŀ��*/
         i.setLayoutParams(new Gallery.LayoutParams(200, 240));
         /*����Gallery�0�2����ͼ*/
         i.setBackgroundResource(mGalleryItemBackground);
         /*����imageView����*/
         return i; 
       }  
     }
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
//		mp.release();
        Intent intent = new Intent();
        intent.setClass(Smxx.this, DirectPy.class);
        startActivity(intent);
        Smxx.this.finish();
		super.onBackPressed();
	} 
}