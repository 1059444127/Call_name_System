package com.example.call_name_system.util;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import com.example.viewpagetest.secondFragment;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class myService extends Service{
	private String url;
	private static final String  BROADCAST="COUNT_NUM";
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		getDate_count();
		
	}

	private void getDate_count() {
		
		//创建一个定时器	，实现每过一段时间发送广播
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
		//没过一秒发送一条广播
			@Override
			public void run() {
				try {
					String date  = HttpUtils.sendGetRequest(new URL(Https.IP_ADDRESS+"/get_order_conun"));
Log.v("Cander_num", date);					
					Intent intent = new Intent();
					intent.setAction(BROADCAST);
					intent.putExtra("Cander_num",date );
					sendBroadcast(intent);
					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				
				
			}
		}, 1000,1000);
		
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	
	
}
