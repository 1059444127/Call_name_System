package com.example.four_acticity;

import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.call_name_system.util.Https;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;



import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;


public class MySocketIO implements IOCallback  {

	public  SocketIO socket;

	private Handler mhandler;

	
	
	//¹¹Ôìº¯Êý
	public MySocketIO( Handler handler){
		mhandler=handler;
		 socket = new SocketIO(); 
		 try {
			socket.connect(Https.IP_ADDRESS+"/", this);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	@Override
	public void onDisconnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onConnect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(String data, IOAcknowledge ack) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMessage(JSONObject json, IOAcknowledge ack) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("null")
	@Override
	public void on(String event, IOAcknowledge ack, Object... args) {
		// TODO Auto-generated method stub
		
		
//Log.e("Even",event.equals("ServerBroadcast")+" "+args.length);				
		if(event.equals("ServerBroadcast")){			
			JSONObject jsonObject=null;
			
			
			if(args.length>0){				
				try {
					Message msg=new Message();
					msg.what=3;
					jsonObject=new JSONObject(args[0].toString());
					Bundle bundle = new Bundle();
//Log.e("Evens",jsonObject.getString("CompanyName")+jsonObject.getString("CurrentNo")+jsonObject.getString("CurrentType"));					
					bundle.putString("dinner_name", jsonObject.getString("CompanyName").toString());
					bundle.putString("call_num", jsonObject.getString("CurrentNo").toString());
					bundle.putString("Cander", jsonObject.getString("CurrentType").toString());
//Log.e("Evens", mhandler+"");					
					msg.setData(bundle);
					mhandler.sendMessage(msg);
					Message message = new Message();
					message.what=1;
//Log.e("Even",mhandler.getClass()+"");					
					mhandler.sendMessage(message);
				} catch (JSONException e) {
					
					e.printStackTrace();
				}
			
			}
			
			
		}
		
		
	
		
	}


	@Override
	public void onError(SocketIOException socketIOException) {
		// TODO Auto-generated method stub
		
	}


	
}
