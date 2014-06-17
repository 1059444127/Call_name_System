package com.example.normalguestlogin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.call_name_system.R;
import com.example.call_name_system.util.HttpUtils;
import com.example.call_name_system.util.Https;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class Callingsurface extends Activity {

	Button btn1, btn2;
	ListView lv1;
	private List<HashMap<String, Object>> listItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.callingsurface);
		btn1 = (Button) this.findViewById(R.id.find2);
		// List<HashMap<String,Object>> listItem = new
		// ArrayList<HashMap<String,Object>>();
		listItem = getData();
		Madapt madapt = new Madapt(); 
		btn1.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

			}
		});
		btn2 = (Button) this.findViewById(R.id.bcak2);
		btn2.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent = new Intent();
				intent.setClass(Callingsurface.this, Normalguestsurface.class);
				startActivity(intent);
				Callingsurface.this.finish();
			}
		});

		lv1 = (ListView) this.findViewById(R.id.callingsurlv);
		lv1.setAdapter(madapt);
		lv1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
			
				Intent intent = new Intent();
				intent.putExtra("username",listItem.get(arg2).get("username").toString());
				intent.putExtra("dinner_name",listItem.get(arg2).get("dinner_name").toString());
				intent.setClass(Callingsurface.this, Yuyue.class);
				startActivity(intent);
				Callingsurface.this.finish();
			}
		});

	}

	private class Madapt extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listItem.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return listItem.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			arg1 = LayoutInflater.from(Callingsurface.this).inflate(
					R.layout.callinglistview, null);
			TextView order_dinner = (TextView) arg1
					.findViewById(R.id.order_dinner);
			TextView dinner_time = (TextView) arg1
					.findViewById(R.id.dinner_time);
			TextView dinner_rot = (TextView) arg1.findViewById(R.id.dinner_rot);
			TextView size = (TextView) arg1.findViewById(R.id.size);

			if (listItem.get(0) != null) {
				order_dinner.setText("餐厅名   "
						+ listItem.get(arg0).get("dinner_name"));
				dinner_time.setText("营业时间   "
						+ listItem.get(arg0).get("sellTime"));
				dinner_rot.setText("管理者   "
						+ listItem.get(arg0).get("username"));
				size.setText("餐厅规模,能容纳" + listItem.get(arg0).get("scale") + "人");
			} else {
				order_dinner.setText("");
				dinner_time.setText("");
				dinner_rot.setText("");
				size.setText("");

			}

			return arg1;
		}

	}

	private List<HashMap<String, Object>> getData() {
		String data = null;
		List list;
		try {
			data = HttpUtils.sendGetRequest(new URL(Https.IP_ADDRESS
					+ "/get_dinnerlogin"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.v("mes", data);
		JSONArray array = null;
		if (data.length() > 0) {
			try {
				array = new JSONArray(data);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 list = new ArrayList();
			Map<String, Object> map = null;
			for (int i = 0; i < array.length(); i++) {
				map = new HashMap<String, Object>();
				try {
					JSONObject jsonObject = (JSONObject) array.get(i);
					map.put("dinner_name", jsonObject.get("dinner_name"));
					map.put("sellTime", jsonObject.get("sellTime"));
					map.put("username", jsonObject.get("username"));
					map.put("scale", jsonObject.get("scale"));
					list.add(map);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			return list;
		} else if (data.length() == 0) {
			return null;
		} else {
			Toast.makeText(Callingsurface.this, "获取数据失败", Toast.LENGTH_LONG)
					.show();

		}
         return null;
	}

}
