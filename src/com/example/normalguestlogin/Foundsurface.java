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
import com.example.call_name_system.util.Bean;
import com.example.call_name_system.util.HttpUtils;
import com.example.call_name_system.util.Https;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Foundsurface extends Activity{

	Button bt1;
	ListView lv1;
	private List<HashMap<String, Object>> listItem;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fingmes);
		listItem =getData();
		Madapt madapt = new Madapt();
		bt1 = (Button)this.findViewById(R.id.bcak3);
		lv1 = (ListView)this.findViewById(R.id.listView2);
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.setClass(Foundsurface.this, Normalguestsurface.class);
				startActivity(intent);
				Foundsurface.this.finish();
			}
		});
      lv1.setAdapter(madapt);
 	  lv1.setOnItemClickListener(new OnItemClickListener() {

 		@Override
 		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
 				long arg3) {
 			// TODO Auto-generated method stub
 			Intent intent = new Intent();
 			intent.putExtra("call_num",listItem.get(arg2).get("call_num")+"");
 			intent.putExtra("dinner_name",listItem.get(arg2).get("dinner_name")+"");
 			intent.putExtra("order_type",listItem.get(arg2).get("order_type")+"");
			intent.setClass(Foundsurface.this, Broadcast.class);
			startActivity(intent);
			Foundsurface.this.finish();
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
			arg1 = LayoutInflater.from(Foundsurface.this).inflate(
					R.layout.item, null);
			TextView dinner_name = (TextView) arg1
					.findViewById(R.id.dinner_n);
			
			TextView order_type = (TextView) arg1
					.findViewById(R.id.order_type);
			

			if (listItem.get(0) != null) {
				dinner_name.setText("餐厅名   "
						+ listItem.get(arg0).get("dinner_name"));
				
				order_type.setText("预约类型   "
						+ listItem.get(arg0).get("order_type"));
			} else {
				dinner_name.setText("");
				order_type.setText("");

			}

			return arg1;
		}

	}
	private List<HashMap<String, Object>> getData() {
		String data = null;
		List list;
		try {
			Map map = new HashMap();
			map.put("dinner_name",Bean.name);
			data = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/get_call"), map, "utf-8");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
Log.v("mes", data);
		JSONArray array = null;
		if (data!=null) {
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
					map.put("order_type", jsonObject.get("order_type"));
					map.put("call_num", jsonObject.get("call_num"));
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
			Toast.makeText(Foundsurface.this, "获取数据失败", Toast.LENGTH_LONG)
					.show();

		}
         return null;
	}





}
