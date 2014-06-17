package com.example.call_name_system.logic;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import com.example.call_name_system.util.HttpUtils;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.os.StrictMode;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
@SuppressLint("NewApi")
public class logic {
 private String str="";	
 private Map<String, String> params=null;
   @SuppressLint("NewApi")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
   public logic(String str,Map<String, String> params){
	   StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
		.detectDiskReads().detectDiskWrites().detectNetwork()
		.penaltyLog().build());
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
		.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
		.penaltyLog().penaltyDeath().build()); 	 
        
        this.str=str;
        this.params=params;
   }
  public String getDate(){
	  
	  
	  try {
		return HttpUtils.submitPostData(new URL(str), params, "utf-8");
	} catch (MalformedURLException e) {
	    System.out.println("¡¨Ω” ß∞‹");
		throw new RuntimeException(e);
	}
	  
  }

}
