package com.example.normalguestlogin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.call_name_system.R;
import com.example.call_name_system.util.Bean;
import com.example.call_name_system.util.HttpUtils;
import com.example.call_name_system.util.Https;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Yuyue  extends Activity{

	TextView tv1;
	Button bt1,bt2;
	String allnum;
	int candernum,roomnum;
	int s=1;
	private Timer timer,timer2;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.yuyuexxjm);
		tv1=(TextView)this.findViewById(R.id.yuyuexx);
		bt1=(Button)this.findViewById(R.id.yuyuec);
		bt2=(Button)this.findViewById(R.id.back5);
		final Intent intent = getIntent();
Log.v("mes", "����Ա"+intent.getStringExtra("username"));		
		//���ԤԼ��
   allnum= order_all(intent.getStringExtra("username"))+"";
		tv1.setText(intent.getStringExtra("dinner_name")+"���е�ԤԼ����"+allnum+"��,Ŀǰ���û���"+candernum+"��,���仹��"+roomnum+"��.");
		bt1.setOnClickListener(new View.OnClickListener() {
		String str []={"Cander","Room"};
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Builder builder = new AlertDialog.Builder(Yuyue.this);
						builder.setItems(new String[]{"����ԤԼ","����ԤԼ"}, new AlertDialog.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
							//�жϼ��ϵ�����  
								String dinner =intent.getStringExtra("dinner_name");
								String p = getnum(intent.getStringExtra("dinner_name"),str[which] , "find");
							  int i = Integer.parseInt(p);
								if(i==0){
								Map map2 = new HashMap();
								map2.put("username",Bean.name);
								map2.put("dinner_name", dinner);
								map2.put("call_num", s+"");
								map2.put("order_type",str[which]);
								Map map3 = new HashMap();
								map3.put("username",Bean.name);
								map3.put("dinner_name", dinner);
								map3.put("num_order", s+"");
								map3.put("order",str[which]);
								map3.put("Time_order","");
								
								
//Log.e("dinner_name",intent.getStringExtra("dinner_name")+"   ..." );
                               try {
								String result = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/save_call"), map2, "utf-8") ;
								String results = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/order_table"), map3, "utf-8") ;
								if(result.equals("success")){
									//update_c
									int pst;//����ѡ������ĸ�ԤԼ����ԤԼ��
									if(str[which].equals("Cander")){
										pst=	candernum--;
										candernum = pst;
									}else{
										pst= roomnum--;
										roomnum =pst;
									}
									Map map = new HashMap();
									map.put("order_type",str[which]);
								    map.put("username",intent.getStringExtra("username"));
								    map.put("number",pst+"");
								    //Ӱ�쵽���ݿ�
								    String resultss = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/update_c"), map, "utf-8") ; 
								    if(resultss.equals("success")){
								    	Toast.makeText(Yuyue.this, "ԤԼ�ɹ�", Toast.LENGTH_SHORT).show();
								    	tv1.setText("�ù�˾��ԤԼ����"+allnum+"��,Ŀǰ���û���"+candernum+"��,���仹��"+roomnum+"��.");
								    }else{
								    	Toast.makeText(Yuyue.this, "��æ....", Toast.LENGTH_SHORT).show();
								    	
								    }
								    
								    
								    
								}
								
								
//Log.e("��Ϣ", result+"   "+Bean.name);								
							} catch (MalformedURLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}                               

							  }else{
								  Map map2 = new HashMap();
									map2.put("username",Bean.name);
									map2.put("dinner_name", dinner);
									
									map2.put("call_num", ++s+"");
									
									map2.put("order_type",str[which]);
									
									  Map map3 = new HashMap();
									    map3.put("username",Bean.name);
										map3.put("dinner_name", dinner);
										map3.put("num_order", s+"");
										map3.put("order",str[which]);
										map3.put("Time_order","");
	//Log.e("dinner_name",intent.getStringExtra("dinner_name")+"   ..." );
	                               try {
	                            	String results = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/save_call"), map2, "utf-8") ;  
									String result = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/order_table"), map3, "utf-8") ;
									if(result.equals("success")){
										//update_c
										int pst;//����ѡ������ĸ�ԤԼ����ԤԼ��
										if(str[which].equals("Cander")){
											pst=--candernum;
											candernum = pst;
										}else{
											pst= --roomnum;
											roomnum=pst;
											
										}
										Map map = new HashMap();
										map.put("order_type",str[which]);
									    map.put("username",intent.getStringExtra("username"));
									    map.put("number",pst+"");
									    //Ӱ�쵽���ݿ�
Log.e("����",str[which]+"   "+intent.getStringExtra("username")+"   "+pst);									    
									    String resultst = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/update_c"), map, "utf-8") ; 
									    if(resultst.equals("success")){
									    	Toast.makeText(Yuyue.this, "ԤԼ�ɹ�", Toast.LENGTH_SHORT).show();
									    	tv1.setText("�ù�˾��ԤԼ����"+allnum+"��,Ŀǰ���û���"+candernum+"��,���仹��"+roomnum+"��.");
									    	
									    }else{
									    	Toast.makeText(Yuyue.this, "��æ....", Toast.LENGTH_SHORT).show();
									    	
									    }
									    
									    
									    
									}
									
									
									
//	Log.e("��Ϣ", result+"   "+Bean.name);								
								} catch (MalformedURLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}                        
								  
								  
								  
							  }
								
								
								dialog.dismiss();
							}
							

						});
						builder.show();
			}
		});
		bt2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Yuyue.this, Callingsurface.class);
				startActivity(intent);
				Yuyue.this.finish();
				
			}
		});
	}
	private int order_all(String str){
		String data = null;
		String Cander="0",Room="0";
		JSONArray jsonArray = null;
		Map map = new HashMap();
		map.put("username", str);
		try {
		data=HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/getcompany_plan"), map, "utf-8");
		if(data.length()>0){
			jsonArray = new JSONArray(data);
			
			for(int i =0;i<jsonArray.length();i++){
			 JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			 Cander =jsonObject.getString("Cander_num");
			 Room =jsonObject.getString("Room_num");
			}
		}	
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        int canders = Integer.parseInt(Cander);
        int rooms = Integer.parseInt(Room);
        candernum =canders;
        roomnum =rooms;
		return canders+rooms;
	}
	//���
	public String getnum(String dinner_name,String type,String str){
		Map map = new HashMap();
		map.put("dinner_name",dinner_name);
		map.put("order_type",type); 
		map.put("method", str);
		String date=null;
		try {
			 date = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/find_call"), map, "utf-8");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

}
