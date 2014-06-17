package com.example.normalguestlogin;

import com.example.call_name_system.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Person extends Activity{

	TextView tv1;
	Button bt1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.personalmes);
		
		tv1=(TextView)this.findViewById(R.id.uesrname);
		bt1=(Button)this.findViewById(R.id.bcak4);
		bt1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent intent = new Intent();
				intent.setClass(Person.this, Normalguestsurface.class);
				startActivity(intent);
				Person.this.finish();
			}
		});
	}
	

}
