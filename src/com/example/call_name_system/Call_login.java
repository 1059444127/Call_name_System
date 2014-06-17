package com.example.call_name_system;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.call_name_system.util.Bean;
import com.example.call_name_system.util.HttpUtils;
import com.example.call_name_system.util.Https;
import com.example.normalguestlogin.Normalguestsurface;
import com.example.viewpagetest.Viewpage;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Call_login extends Activity{
    private Spinner sp;
    private EditText editText,editText2;
    private CheckBox box;
    private Button login,resgise;
    private TextView t1,t2;
    protected ArrayAdapter<CharSequence> mAdapter;
    int i ;
    
    private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			if(msg.what==1){
				i=1;
			}
		    if(msg.what==2){
		    	i=2;
		    }
		    if(msg.what==3){
		    	i=3;
		    }
		}
    	
    	
    	
    };
    
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
			.detectDiskReads().detectDiskWrites().detectNetwork()
			.penaltyLog().build());
	        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
			.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
			.penaltyLog().penaltyDeath().build()); 	 
		final String str[]={"root","企业用户","一般用户"};
	   init();	
	   this.mAdapter = new ArrayAdapter(this,
               android.R.layout.simple_spinner_item, str);
	   sp.setAdapter(this.mAdapter);  
	   sp.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {

		@Override
		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
		    if(arg2==0){
		    	Message message = new Message();
		    	message.what=1;
		    	handler.sendMessage(message);
		    }
		    if(arg2==1){
		    	Message message = new Message();
		    	message.what=2;
		    	handler.sendMessage(message);
Log.v("mes", str[arg2]);		    	
		    }
		    if(arg2==2){
		    	Message message = new Message();
		    	message.what=3;
		    	handler.sendMessage(message);
		    }
		    
		    
			arg0.setVisibility(View.VISIBLE);  
		}

		@Override
		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
	});
		
	   login.setOnClickListener(new Button.OnClickListener() {	
		@Override
		public void onClick(View arg0) {
			String username = editText.getText().toString();
			String password = editText2.getText().toString();
		  if(i==2){
			  Map map = new HashMap();
			  map.put("username", username);
			  map.put("password", password);
			  String data=null;
			  try {
				 data = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/logins"), map, "utf-8");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			  if(data.equals("success")){
				  Bean.dinner_root=username;
				  try {
					  Map map2 = new HashMap();
					  map2.put("username", username);
					 //拿到餐厅名 
					String str =HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/getdinner"), map2, "utf-8");
					JSONArray array = new JSONArray(str);
					JSONObject jsonObject = (JSONObject) array.get(0);
					Bean.dinner_name = jsonObject.getString("dinner_name");
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				  SharedPreferences preferences = getSharedPreferences("userInfo",   
			                Activity.MODE_PRIVATE);   
			      Editor editor = preferences.edit();
			      editor.putString("username", username);
			      editor.putString("password", password);
			      editor.commit();
				  
				  
				  
			  Intent intent = new Intent();
			  intent.setClass(Call_login.this, Viewpage.class);
			  startActivity(intent);
			  finish();
			  }else{
				 Toast.makeText(Call_login.this, "登陆失败", Toast.LENGTH_SHORT).show(); 
			  }
		  }
		  if(i==3){
			  Map map = new HashMap();
			  map.put("username", username);
			  map.put("password", password);
			  String data=null;
			  try {
				 data = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/login"), map, "utf-8");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  
			  if(data.equals("success")){
				  Bean.name=username;
				  SharedPreferences preferences = getSharedPreferences("userInfo",   
			                Activity.MODE_PRIVATE);   
			      Editor editor = preferences.edit();
			      editor.putString("username", username);
			      editor.putString("password", password);
			      editor.commit();
				  
				  
				  
			  Intent intent = new Intent();
			  intent.setClass(Call_login.this, Normalguestsurface.class);
			  startActivity(intent);
			  finish();
			  
		  }	else{
				 Toast.makeText(Call_login.this, "登陆失败", Toast.LENGTH_SHORT).show(); 
			  }
		  
		  
		}
		}
		});;
		resgise.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				if(i==2){
				Intent intent = new Intent();
				intent.setClass(Call_login.this,Reginer.class);
				startActivity(intent);
				finish();
				}
				if(i==3){
					Intent intent = new Intent();
					intent.setClass(Call_login.this,Reginers.class);
					startActivity(intent);
					finish();	
					
				}
			}
		});
		

	}

	private void init() {
		t1 = (TextView) findViewById(R.id.textView1);
		t2 = (TextView) findViewById(R.id.textView2);
		sp = (Spinner) findViewById(R.id.spinner1);
		editText = (EditText) findViewById(R.id.editText1);
		editText2 = (EditText) findViewById(R.id.editText2);
		login = (Button) findViewById(R.id.button1);
		resgise = (Button) findViewById(R.id.button2);
	}
	
	
	
}