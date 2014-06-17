package com.example.four_acticity;


import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import com.example.call_name_system.R;
import com.example.call_name_system.util.Bean;
import com.example.call_name_system.util.HttpUtils;
import com.example.call_name_system.util.Https;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Four_acticity extends Fragment {
	private TextView text, call_num,texts,room_num;
	private Button call_but, next_but, set_but,room_call,room_net,room_set;
	private int call_nums=0,room_nums=0;
    private MySocketIO socket;
    protected View         mMainView;
    private EditText et_search;
    private Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				break;
			case 2:
				 Bundle bundle = msg.getData();
	          String    dinner_name = bundle.getString("dinner_name");
	  Log.e("dinner_name", dinner_name);        
				break;
			default:
				break;
			}
			
			
		}
    	
    	
    };
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		mMainView = inflater.inflate(R.layout.call_main, null);
		text = (TextView) mMainView.findViewById(R.id.call_name);
		texts = (TextView) mMainView.findViewById(R.id.Room_call);
		
		
		call_num = (TextView) mMainView.findViewById(R.id.call_conten);
		room_num = (TextView) mMainView.findViewById(R.id.call_room);
		
		call_but = (Button) mMainView.findViewById(R.id.call_button);
		room_call = (Button) mMainView.findViewById(R.id.Room_calls);
		
		next_but = (Button)mMainView.findViewById(R.id.next_button);
		room_net = (Button)mMainView.findViewById(R.id.Room_next);
		
		
		set_but = (Button) mMainView.findViewById(R.id.set_button);
		room_set = (Button) mMainView.findViewById(R.id.Room_set);
		
		 call_but.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
Log.e("Even", call_nums+"...");		
                Map map = new HashMap();
                map.put("CompanyName", Bean.dinner_name);
                map.put("CurrentNo", call_nums+"");
                map.put("CurrentType", "Cander");
				try {
					String result = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/broadcase"), map, "utf-8");
Log.e("Even", result);					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		  //下一号
				next_but.setOnClickListener(new Button.OnClickListener() {

					public void onClick(View v) {
		             ++call_nums;
		             call_num.setText("当前叫号数为    "+call_nums);
					}
				});
		
				set_but.setOnClickListener(new Button.OnClickListener() {

					public void onClick(View v) {
		             
						Builder dialog = new AlertDialog.Builder(Four_acticity.this.getActivity());
						dialog.setTitle("设置叫号数");
						   LayoutInflater inflater = (LayoutInflater) Four_acticity.this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						   LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.dialog, null);
						   dialog.setView(layout);
						   et_search = (EditText)layout.findViewById(R.id.dialog_edit);

						   
						   dialog.setPositiveButton("叫号", new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog, int which) {
						    	
						    	
								   if(isNumber(et_search.getText().toString())){
									   call_nums=Integer.parseInt(et_search.getText().toString());
	//	Log.d("mes","true");		   socket = new MySocketIO(handler,call_nums,"Cander");				   
								   }else{
									 Toast.makeText(Four_acticity.this.getActivity(), "设置失败", Toast.LENGTH_SHORT).show();  
								   }
								    
						    
						    }
						   });
						  
						   dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog, int which) {
						    
						    }
						   
						   });
						   dialog.show();
						
						
						
						
						
					}
				});
	//下一位
				room_net.setOnClickListener(new Button.OnClickListener() {

					public void onClick(View v) {
						++room_nums;
						room_num.setText("当前叫号数为    "+room_nums);
					}
				});
	//房间叫号			
				room_call.setOnClickListener(new Button.OnClickListener() {

					public void onClick(View v) {
						 Map map = new HashMap();
			                map.put("CompanyName", Bean.dinner_name);
			                map.put("CurrentNo", room_nums+"");
			                map.put("CurrentType", "Room");
							try {
								String result = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/broadcase"), map, "utf-8");
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					}
				});
   //房间叫号设置				
				room_set.setOnClickListener(new Button.OnClickListener() {

					public void onClick(View v) {
						Builder dialog = new AlertDialog.Builder(Four_acticity.this.getActivity());
						dialog.setTitle("设置叫号数");
						   LayoutInflater inflater = (LayoutInflater) Four_acticity.this.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						   LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.dialog, null);
						   dialog.setView(layout);
						   et_search = (EditText)layout.findViewById(R.id.dialog_edit);
						   
						   dialog.setPositiveButton("叫号", new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog, int which) {
								   if(isNumber(et_search.getText().toString())){
									   room_nums=Integer.parseInt(et_search.getText().toString());
	//	Log.d("mes","true");		   socket = new MySocketIO(handler,room_nums,"Cander");				   
								   }else{
									 Toast.makeText(Four_acticity.this.getActivity(), "设置失败", Toast.LENGTH_SHORT).show();  
								   }
						    
						    }
						   });
						  
						   dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
						    public void onClick(DialogInterface dialog, int which) {
						    
						    }
						   
						   });
						   dialog.show();
						
						
						
					}
				});
				
				
				
				
		return mMainView;
	}

	public static boolean isNumber(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		
		
		return pattern.matcher(str).matches();
	}
}
