package com.example.normalguestlogin;

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


public class mSocketIO implements IOCallback  {

	private SocketIO socket;

	private Handler mhandler;
	private int call_num;
	//¹¹Ôìº¯Êý
	public mSocketIO( Handler handler)
	{
		mhandler=handler;
		
		socket = new SocketIO();
		/*try {
			
			socket.connect(Https.IP_ADDRESS+"/", this);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
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

	@Override
	public void on(String event, IOAcknowledge ack, Object... args) {
		// TODO Auto-generated method stub
		if(event.equals("welcome"))
		{
			Message msg=new Message();
			msg.what=1;
			mhandler.sendMessage(msg);
		}
Log.e("Even",event.toString());		
		if(event.endsWith("Cander_Broadcast")){
			Message msg=new Message();
			msg.what=2;
			JSONObject jsonObject=null;
			Bundle bundle = null;
			
			if(args.length>0){
				jsonObject = (JSONObject) args[0];
				try {
					bundle.putString("dinner_name", jsonObject.getString("dinner_name"));
					bundle.putString("call_num", jsonObject.getString("call_num"));
					bundle.putString("Cander", jsonObject.getString("Cander"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			msg.setData(bundle);
			mhandler.sendMessage(msg);
		}
		if(event.equals("Room_Broadcast")){
			Message msg=new Message();
			msg.what=3;
			 JSONObject jsonObject=null;
			Bundle bundle = null;
			
			if(args.length>0){				
				try {
					jsonObject=new JSONObject(args[0].toString());
					bundle.putString("dinner_name", jsonObject.getString("dinner_name"));
					bundle.putString("call_num", jsonObject.getString("call_num"));
					bundle.putString("Cander", jsonObject.getString("Cander"));
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			msg.setData(bundle);
			mhandler.sendMessage(msg);
		}
	
	
	
	
	}


	@Override
	public void onError(SocketIOException socketIOException) {
		// TODO Auto-generated method stub
		
	}


	
}
