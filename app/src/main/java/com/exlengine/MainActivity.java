package com.exlengine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	int nowView =0;
	MainView mainview;
	Aview aview;

	SurfaceView ViewList[]={mainview,aview};
	Intent intent;
	Intent deintent;

	public void changeView(int what)//從各個SurfaceView傳送訊息
	{
		Message msg = myHandler.obtainMessage(what);
		myHandler.sendMessage(msg);
		nowView =what;
	}

	Handler myHandler = new Handler(){//處理各個SurfaceView傳送的訊息
		public void handleMessage(Message msg) {
			int view_num=msg.what;
			if(ViewList[view_num]==null)
			{
				SurfaceView tmp_view;
				switch (view_num){
					case 0:
						tmp_view=new MainView(MainActivity.this);
						break;
					case 1:
						tmp_view=new Aview(MainActivity.this);
						break;
					default:
						tmp_view=new SurfaceView(MainActivity.this);
						break;
				}
				ViewList[view_num]=tmp_view;
			}
			setContentView(ViewList[view_num]);
			ViewList[view_num].requestFocus();
			ViewList[view_num].setFocusableInTouchMode(true);
			Log.e("view load",""+view_num);

		}
	};

	public void callToast(String str)//Toast訊號發送
	{
		Message msg = toastHandler.obtainMessage(0,str);
		toastHandler.sendMessage(msg);

	}
	Handler toastHandler = new Handler(){//Toast接收並觸發
		public void handleMessage(Message msg) {
			Toast.makeText(MainActivity.this, msg.obj.toString(), Toast.LENGTH_LONG).show();
		}
	};


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//游戲過程中只容許調整多媒體音量，而不容許調整通話音量
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉標題
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉標頭
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//強制橫屏
		//this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//強制直屏
		changeView(0);//進入"0"界面
	}
	
	@Override
	public boolean onKeyDown(int keyCode,KeyEvent e)
	{
		if(keyCode==4)
		{
			switch(nowView)
			{
			case 1:
				Constant.Flag=false;
				this.changeView(0);
				break;
			case 0:
				System.exit(0);
				break;

			}
			return true;
		}
		/*if(keyCode==e.KEYCODE_HOME){
			 System.exit(0);
			return true;
		}*/
		return false;

	}



	@Override
	public void onResume(){
		Constant.setFlag(true);
		changeView(nowView);
		DisplayMetrics dm=new DisplayMetrics();
		if(Build.VERSION.SDK_INT>=19){
			this.getWindow().getDecorView();
			getWindow().getDecorView().setSystemUiVisibility(
					View.SYSTEM_UI_FLAG_LAYOUT_STABLE
							| View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
							| View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
							| View.SYSTEM_UI_FLAG_FULLSCREEN
							| View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
							| View.INVISIBLE);
			getWindowManager().getDefaultDisplay().getRealMetrics(dm);
		}else{
			//取得解析度
			getWindowManager().getDefaultDisplay().getMetrics(dm);
		}
		//給常數類別中的螢幕高和寬給予值
		if(dm.widthPixels>dm.heightPixels)
		{
			Constant.SYSTEM_WIDTH=dm.widthPixels;
			Constant.SYSTEM_HIGHT=dm.heightPixels;
		}else
		{
			Constant.SYSTEM_HIGHT=dm.widthPixels;
			Constant.SYSTEM_WIDTH=dm.heightPixels;
		}
		if(Constant.SYSTEM_HIGHT>Constant.SYSTEM_WIDTH/16*9) {//將螢幕固定為16:9
			Constant.SCREEN_HIGHT = Constant.SYSTEM_WIDTH / 16 * 9;//Y座標校正
			Constant.SCREEN_WIDTH=Constant.SYSTEM_WIDTH;
		}
		else{
			Constant.SCREEN_WIDTH = Constant.SYSTEM_HIGHT / 9 * 16;//X座標校正
			Constant.SCREEN_HIGHT=Constant.SYSTEM_HIGHT;
		}

		Constant.SCREEN_WIDTH_UNIT = ((float)Constant.SCREEN_WIDTH/Constant.DEFULT_WIDTH);
		Constant.SCREEN_HEIGHT_UNIT= ((float)Constant.SCREEN_HIGHT/Constant.DEFULT_HIGHT);
		super.onResume();
	}
	@Override
	public void onPause(){
		Constant.setFlag(false);
		super.onPause();
	}
}
