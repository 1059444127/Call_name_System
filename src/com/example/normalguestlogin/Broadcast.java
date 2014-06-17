package com.example.normalguestlogin;

import com.example.call_name_system.R;
import com.example.four_acticity.MySocketIO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Broadcast extends Activity{

	TextView tv1;
	Button bt1;
	MySocketIO io;
	String dinner_name,call_num,order;
	private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			
			if(msg.what==3){
//Log.e("Even", "i coming"+call_num);
              Bundle bundle = msg.getData();
              dinner_name = bundle.getString("dinner_name");
              call_num = bundle.getString("call_num");
              order = bundle.getString("Cander");
              tv1.setText(call_num);
			}
		
		}
		
		
		
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast);
		tv1 = (TextView)this.findViewById(R.id.broadcast);
		bt1=(Button)this.findViewById(R.id.back6);
		 bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Broadcast.this, Foundsurface.class);
				startActivity(intent);
				Broadcast.this.finish();
			}
		});
	
				io = new MySocketIO(handler);
		
		
		
		
		Intent intent = getIntent();
		String num = intent.getStringExtra("call_num");
		String dinner_name1 = intent.getStringExtra("dinner_name");
		String ordertype = intent.getStringExtra("order_type");
		
//		Log.e("集合中的叫号数",num+"   "+dinner_name+"   "+ordertype );		
		if(num.equals(call_num)&&dinner_name1.equals(dinner_name)&&ordertype.equals("order")){
//已到当前号码
		Toast.makeText(Broadcast.this, "请到"+order, Toast.LENGTH_LONG).show();	
			
		}	
	
	
	
	
	
	}

	
}
