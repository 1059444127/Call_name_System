package com.example.call_name_system;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.example.call_name_system.util.HttpUtils;
import com.example.call_name_system.util.Https;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reginer extends Activity{
    private TextView qiye,root,passw,sell,size;
    private EditText qiye_e,root_e,passw_e,sell_e,size_e,login_e,login_pe; 
    private Button reginse,cancle;
    private String i ;
    private Map map = new HashMap();
    String result;
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.register);
			
			 StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build()); 	 
			
			
			qiye = (TextView) findViewById(R.id.qiye);
			root = (TextView) findViewById(R.id.qiye_root);
			size = (TextView) findViewById(R.id.scale);
			sell = (TextView) findViewById(R.id.selltime);
			passw = (TextView) findViewById(R.id.password_qiye);
		
			
			qiye_e = (EditText) findViewById(R.id.qiye_edi);
			root_e = (EditText) findViewById(R.id.qiye_root_edi);
			passw_e = (EditText) findViewById(R.id.password_qiye_edi);
			sell_e = (EditText) findViewById(R.id.selltime_edi);
			size_e = (EditText) findViewById(R.id.scale_edi);
			
			reginse = (Button) findViewById(R.id.qiye_regi);
			cancle = (Button) findViewById(R.id.calse);
			
		
			
			
		 
		
			reginse.setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				String dinner_name = qiye_e.getText().toString();
				String username = root_e.getText().toString();
				String password = passw_e.getText().toString();
				String sellTime = sell_e.getText().toString();
				String scale = size_e.getText().toString();
				
				map.put("dinner_name", dinner_name);
				map.put("username",username);
				map.put("password", password);
				map.put("sellTime", sellTime);
				map.put("scale", scale);
				
					try {
						result = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/dologin_dinner"), map, "utf-8");
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(result.equals("success")){
						Toast.makeText(Reginer.this, "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(Reginer.this, "×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
					}
				
					
				}
			});
			cancle.setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(Reginer.this, Call_login.class);
				startActivity(intent);
				finish();
				}
			});
			
			
		/*}else if(i .equals("3")){
			setContentView(R.layout.activity_register);
			login = (TextView) findViewById(R.id.login_user_input);
			login_p = (TextView) findViewById(R.id.login_password_input);
			
			login_e = (EditText) findViewById(R.id.username_edit);
			login_pe = (EditText) findViewById(R.id.password_edit);
			
			login_c = (Button) findViewById(R.id.reg_regReturn);
			login_r  = (Button) findViewById(R.id.reg_regBtn);
			
			
			
			final Map map = new HashMap();
			
			map.put("username", login_e.getText().toString());
			map.put("password", login_pe.getText().toString());

			login_r.setOnClickListener(new Button.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					try {
						String result = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/dologin"), map, "utf-8");
						if(result.equals("success")){
							Toast.makeText(Reginer.this, "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
						}else{
							Toast.makeText(Reginer.this, "×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
						}
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}		
					
				}
			});
			
			
		}*/
	}

	
	
	
	
}
