package com.example.normalguestlogin;

import com.example.call_name_system.R;

import android.media.Image;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Normalguestsurface extends Activity {
    
	ImageButton ig1,ig2,ig3,ig4;
	ImageView iv1,iv2,iv3;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointment);
    ig1 = (ImageButton)this.findViewById(R.id.yuyue);
    ig2 = (ImageButton)this.findViewById(R.id.chahao);
    ig3 = (ImageButton)this.findViewById(R.id.gerenxinxi);
    ig4 = (ImageButton)this.findViewById(R.id.tuichu);
    iv1 = (ImageView)this.findViewById(R.id.imageView1);
    iv2 = (ImageView)this.findViewById(R.id.imageView2);
    iv3 = (ImageView)this.findViewById(R.id.imageView3);
    ig1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(Normalguestsurface.this, Callingsurface.class);
		startActivity(intent);
		Normalguestsurface.this.finish();
		
		}
	});
    ig2.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(Normalguestsurface.this, Foundsurface.class);
		startActivity(intent);
		Normalguestsurface.this.finish();
		
		}
	});
    ig3.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(Normalguestsurface.this, Person.class);
		startActivity(intent);
		Normalguestsurface.this.finish();
		
		}
	});   
    ig4.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
		Intent intent = new Intent();
		intent.setClass(Normalguestsurface.this, Yuyue.class);
		startActivity(intent);
		Normalguestsurface.this.finish();
		
		}
	});   
    
    }


   
    
}
