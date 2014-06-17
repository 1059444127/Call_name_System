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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Reginers extends Activity{
	 private TextView qiye,root,passw,sell,size,login,login_p;
	    private EditText qiye_e,root_e,passw_e,sell_e,size_e,login_e,login_pe; 
	    private Button reginse,cancle,login_r,login_c;
	    private Map map = new HashMap();
	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork()
		.penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		.penaltyLog().penaltyDeath().build()); 	 
		
		
		login = (TextView) findViewById(R.id.login_user_input);
		login_p = (TextView) findViewById(R.id.login_password_input);
		
		login_e = (EditText) findViewById(R.id.username_edit);
		login_pe = (EditText) findViewById(R.id.password_edit);
		
		login_c = (Button) findViewById(R.id.reg_regReturn);
		login_r  = (Button) findViewById(R.id.reg_regBtn);
		
		
		

		

		login_r.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {

					map.put("username", login_e.getText().toString());
					map.put("password", login_pe.getText().toString());
					String result = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/dologin"), map, "utf-8");
					if(result.equals("success")){
						Toast.makeText(Reginers.this, "×¢²á³É¹¦", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(Reginers.this, "×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
					}
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				
			}
		});
		login_c.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent();
				intent.setClass(Reginers.this,Call_login.class);
				startActivity(intent);
				finish();
				
			}
		});
	
	
	}

	
	
}
