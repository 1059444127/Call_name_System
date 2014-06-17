package com.example.viewpagetest;

import java.net.ContentHandler;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.example.call_name_system.R;
import com.example.call_name_system.logic.logic;

import com.example.call_name_system.util.Bean;
import com.example.call_name_system.util.HttpUtils;
import com.example.call_name_system.util.Https;


import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.format.Time;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class secondFragment extends Fragment {
	private ListView lv;
	TextView content, content_or;
	protected View mMainView;
	private List<HashMap<String, Object>> mData = null;
	private int i = 30;
	private int j = 10;
	private final int HANDLER_NUM = 1;
	private String user = null;
	// 权限
	private boolean call = false, order = false;
	ArrayList<HashMap<String, Object>> list = null;
	private String Cander_num = "0";
	private myAdapt myAdapter;
	private Handler mhandler;
	String dinner_name = null;
	private PagerScrollerActivity pc;
	private Timer timer, timer2;

	// 自定义一个借口来接收数据

	@Override
	public void onDestroy() {

		super.onDestroy();
		timer.cancel();
		timer2.cancel();
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@SuppressLint("NewApi")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		  StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
			.detectDiskReads().detectDiskWrites().detectNetwork()
			.penaltyLog().build());
	        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
			.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
			.penaltyLog().penaltyDeath().build()); 	 
		
		mData = new ArrayList<HashMap<String, Object>>();
		
		// mData.clear();
		// mData.addAll(getData(Https.IP_ADDRESS + "/get_order_table"));
		
		myAdapter = new myAdapt(mData, secondFragment.this.getActivity());
		
		mMainView = inflater.inflate(R.layout.second_fragment, null);
		// content = (TextView) mMainView.findViewById(R.id.content);
		
		//Cander_num = HttpUtils.submitPostData(Https.IP_ADDRESS + "/get_order_conun", params, "utf-8");

		content = (TextView) mMainView.findViewById(R.id.content);
        
		/*content.setText("截止到目前，已有" + Cander_num + "人预约大堂就餐。已有"
				+ getSimData(Https.IP_ADDRESS + "/get_order_conun_rom")
				+ "预约了房间号");*/
		content_or = (TextView) mMainView.findViewById(R.id.content_or);
		lv = (ListView) mMainView.findViewById(R.id.listview_sec);
		// 自定义适配器
		// 获取权限
		get_permisson();

		lv.setAdapter(myAdapter);
		
		//接收信息,并用来接收数据
				mhandler = new Handler(){

					@Override
					public void handleMessage(Message msg) {
						super.handleMessage(msg);
	
						
						if(msg.what==1){
						List list = (List) msg.obj;							

						if(msg.obj!=null){
						mData.clear();
						mData.addAll(list);
						Log.v("mes", "this is get message");  
						myAdapter.notifyDataSetChanged();
						}					
						}
						if(msg.what==2){
						List list = (List) msg.obj;
//Log.v("met",msg.obj+"");												
						if(msg.obj!=null){
						mData.clear();
						mData.addAll(list);
							Log.v("mes", "this is get message");  
							myAdapter.notifyDataSetChanged();
						}
						}
						if(msg.what==3){
							if(msg.getData()!=null){
							Bundle bundle = msg.getData();
								
							
	content.setText("截止到目前，已有" + bundle.getString("Cander_num") + "人预约大堂就餐。已有"
									+ bundle.getString("Room_num")
									+ "预约了房间号");
							Log.v("mes", "this is get message3"+list);  
							}
							
						}
					    if(msg.what==4){
					    	List list = (List) msg.obj;
					    	//Log.v("met",msg.obj+"");												
					    							if(msg.obj!=null){
					    							mData.clear();	
					    							mData.addAll(list);
					    								Log.v("mes", "this is get message");  
					    								myAdapter.notifyDataSetChanged();
					    							}
					    }
					}			
				};
		
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				
				// 自定义Alertdialog
				if (order) {
					Builder builder = new AlertDialog.Builder(
							secondFragment.this.getActivity());
					builder.setItems(new String[] { "删除该预约" },
							new AlertDialog.OnClickListener() {

								@Override
								public void onClick(DialogInterface arg0,
										int arg1) {
                                    
									Map<String, String> map = new HashMap<String, String>();
//Log.v("mes", (String) list.get(arg2).get("username"));
									map.put("username", (String) mData.get(arg2).get("username"));
									logic los = new logic(Https.IP_ADDRESS
											+ "/delect_ord", map);
                                   
									mData.remove(arg2);// 移除Item
									myAdapter.notifyDataSetChanged();
									
									new Thread(new Runnable() {
										
										@Override
										public void run() {
											try {
												Looper.prepare();
																							
												Message message =new Message();
												message.what=2;
												message.obj=getData(Https.IP_ADDRESS + "/get_order_table");
												Thread.sleep(4000);
												mhandler.sendMessage(message);
												Looper.loop();
												mhandler.getLooper().quit();
											} catch (InterruptedException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
											
										}
									}).start();{
										
									};
								    if(los.getDate().equals("success")){
									Toast.makeText(
											secondFragment.this.getActivity(),
											"删除成功", Toast.LENGTH_SHORT).show();
									arg0.dismiss();
								    }
								}
							});
					builder.setTitle((String) mData.get(arg2).get("username"));
					builder.show();
				} else {
					Toast.makeText(secondFragment.this.getActivity(), "你无此权限",
							Toast.LENGTH_LONG).show();
				}
				System.out.println("call " + call + "    order" + order);

			}

		});
		
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				try {
					Looper.prepare();					
					Message message =new Message();
					message.what=1;
					message.obj=getData(Https.IP_ADDRESS + "/get_order_table");
//Log.e("mes",getData(Https.IP_ADDRESS + "/get_order_table").toString())	;				
					Thread.sleep(1000);
					mhandler.sendMessage(message);
					Looper.loop();
					mhandler.getLooper().quit();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block

			}
		}

			
	}).start();
timer = new Timer();		
			
					timer.schedule(new TimerTask() {
						
						@Override
						public void run() {
							try {
													
								Message message =new Message();
								message.what=3;
								Bundle bundle = new Bundle();
								bundle.putString("Cander_num",getSimple(Bean.dinner_name,Https.IP_ADDRESS + "/get_order_conun"));
								bundle.putString("Room_num", getSimple(Bean.dinner_name,Https.IP_ADDRESS + "/get_order_conun_rom"));
								message.setData(bundle);
								Thread.sleep(1000);
								mhandler.sendMessage(message);
								
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
						}
					}, 0,1000);			
	timer2 = new Timer();
	timer2.schedule(new TimerTask() {
		
		@Override
		public void run() {
			Message message =new Message();
			message.what=1;
			message.obj=getData(Https.IP_ADDRESS + "/get_order_table");
//Log.e("mes",getData(Https.IP_ADDRESS + "/get_order_table").toString())	;				
			
			mhandler.sendMessage(message);	
			
		}
	},0,1000);
		
		
		
		return mMainView;
}
private String getSimple(String st,String str){
	  Map map = new HashMap();
	    map.put("dinner_name", st);
	    String date=null;
	    try {
			 date = HttpUtils.submitPostData(new URL(str), map, "utf-8");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return date;
}	
private void get_permisson() {
	    Map map = new HashMap();
	    map.put("username", Bean.dinner_root);
		try {
			String date = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/get_permisson"), map, "utf-8");
			String str = null;
			if(date!=null){
				JSONArray array = new JSONArray(date);
				for(int i =0;i<array.length();i++){
					JSONObject jsonObject = (JSONObject) array.get(i);
					 str =(String) jsonObject.get("permisson");
Log.e("mes", str+"   权限")	;				
				}
			    if(str.equals("叫号")){
			    	call=true;
			    }
			    if(str.equals("预约管理")){
			    	order=true;
			    }
			
			
			}
			
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
class myAdapt extends BaseAdapter{
	private Context content;
	private List<HashMap<String, Object>> list;
	public myAdapt(List list,Context content){
		this.list=list;
		content = content;
	}
	@Override
	public int getCount() {
		
		return  list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		arg1 = LayoutInflater.from(secondFragment.this.getActivity()).inflate(R.layout.second_fragment_item, null);
		TextView userna = (TextView) arg1.findViewById(R.id.order_user);
		TextView order_time = (TextView) arg1.findViewById(R.id.order_time);
		TextView order_num = (TextView) arg1.findViewById(R.id.order_num);
		TextView order = (TextView) arg1.findViewById(R.id.order);
		
		if (list.get(0) != null) {
			userna.setText("用户名   "
					+ list.get(arg0).get("username"));
			order_time.setText("预约时间  "
					+ list.get(arg0).get("Time_order"));
			order_num.setText("预约号   "
					+ list.get(arg0).get("num_order"));
			order.setText("预约类型   "+list.get(arg0).get("order") );
		} else {
			userna.setText("");
			order_time.setText("");
			order_num.setText("");
			order.setText("");

		}
		
		
		
		return arg1;
	}
	
}
	
private List<HashMap<String, Object>> getData(String str){
	String date =null;
	List list;
	try {
		 date = HttpUtils.sendGetRequest(new URL(str));
Log.e("mes",date);		 
		 if(date!=null){
			 JSONArray array = new JSONArray(date);
			 list = new ArrayList();
			 
			 for(int i =0;i<array.length();i++){
				 Map map = new HashMap();
				 JSONObject jsonObject = (JSONObject) array.get(i);
				 String username = jsonObject.getString("username");
				 String Time_order = jsonObject.getString("Time_order");
				 String num_order = jsonObject.getString("num_order");
				 String order = jsonObject.getString("order");
				 String dinner_name = jsonObject.getString("dinner_name");
				 map.put("username", username);
				 map.put("Time_order", Time_order);
				 map.put("num_order", num_order);
				 map.put("username", username);
				 map.put("order", order);
//Log.e("mes",dinner_name.equals(Bean.dinner_name)+"..."+Bean.dinner_name );				 
				 if(dinner_name.equals(Bean.dinner_name))
					list.add(map);
			 }
			 return list;
		 }else{
		Toast.makeText(getActivity(), "数据获取失败", Toast.LENGTH_SHORT).show();
		    return null;
		 }
		 
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   return null;
 
}
	
}