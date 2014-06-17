package com.example.viewpagetest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.example.call_name_system.R;
import com.example.call_name_system.util.Bean;
import com.example.call_name_system.util.HttpUtils;
import com.example.call_name_system.util.Https;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class firstFragment extends Fragment {
	private View view;
	private TextView t1, t2, t3, t4;
	private EditText ed1, ed2, ed3, ed4;
	private Button but1, but2;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view = inflater.inflate(R.layout.first_fragment, null);
		t1 = (TextView) view.findViewById(R.id.Cander_num);
		t2 = (TextView) view.findViewById(R.id.Roo_num);
		t3 = (TextView) view.findViewById(R.id.order_str);
		t4 = (TextView) view.findViewById(R.id.order_end);

		ed1 = (EditText) view.findViewById(R.id.Cander_edi);
		ed2 = (EditText) view.findViewById(R.id.Room_edi);
		ed3 = (EditText) view.findViewById(R.id.order_str_edi);
		ed4 = (EditText) view.findViewById(R.id.order_end_eid);
		but1 = (Button) view.findViewById(R.id.zixing);
		but2 = (Button) view.findViewById(R.id.cancle);

		but1.setOnClickListener(new Button.OnClickListener() 
		{	

			@Override
			public void onClick(View v) {
             Map map = new HashMap();
             map.put("username", Bean.dinner_root);
			 map.put("Cander_num", ed1.getText().toString());
			 map.put("Room_num", ed2.getText().toString());
			 map.put("order_start", ed3.getText().toString());
			 map.put("order_end", ed4.getText().toString());
			 String data=null;
			try {
				 data = data = HttpUtils.submitPostData(new URL(Https.IP_ADDRESS+"/company_plan"), map, "utf-8");
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				if(data.equals("success")){
					Toast.makeText(getActivity(), "Ö´ÐÐ³É¹¦", Toast.LENGTH_SHORT).show();
				}
			
			
			}
		});

		but2.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				ed1.setText("");
				ed2.setText("");
				ed3.setText("");
				ed4.setText("");
				
			}
		});

		return view;
	}

}