package com.example.viewpagetest;
//PagerScrollerActivity是就是一个继承了FragmentActivity抽象的类，实现了项目具体功能（管理TitleIndicator）
import java.util.ArrayList;

import android.app.ActionBar.LayoutParams;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.call_name_system.R;
import com.example.call_name_system.R.color;
import com.example.popuwindow.ActionItem;
import com.example.popuwindow.TitlePopup;

public abstract class PagerScrollerActivity extends FragmentActivity implements OnPageChangeListener {

    private ViewPager            viewPager;
    protected FragmentsAdapter   adapter;
    protected ArrayList<TabInfo> tabs;
    private TitleIndicator       title;
    private int                  defaultTab = 0;
    private ImageButton imb;
    //定义标题栏上的按钮  
    private Button titleBtn;  
      
    //定义标题栏弹窗按钮  
    private TitlePopup titlePopup;  
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fragment_pager);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.theme);
        
        imb = (ImageButton) PagerScrollerActivity.this.findViewById(R.id.imageButton1);
        imb.setOnClickListener(new ImageButton.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			Toast.makeText(PagerScrollerActivity.this, "十字架被敲了",Toast.LENGTH_LONG).show();
			titlePopup.show(v);
			
			}
		});
        
        titlePopup = new TitlePopup(this, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);  
		initData();
        
        
        initView();
    }

	 private void initData(){  
	        //给标题栏弹窗添加子类  
	        titlePopup.addAction(new ActionItem(this, "权限", R.drawable.quanxian));  
	        titlePopup.addAction(new ActionItem(this, "评论",R.drawable.pinglun));  
	        titlePopup.addAction(new ActionItem(this, "注销", R.drawable.zhuxiao));  
	         
	    }  
    
    
    private void initView() throws IndexOutOfBoundsException, NullPointerException {
        setTabsAndAdapter();

        viewPager = (ViewPager) findViewById(R.id.vPager);
        // 设置viewpager内部页面之间的间距
        viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.fragment_viewpager_margin));
       // 设置viewpager内部页面间距的drawable
        viewPager.setPageMarginDrawable(color.myblue);
        
        viewPager.setAdapter(adapter);
        
      //必须让viewPager设置此OnPageChangeListener的实现类，才能对滑动和页面状态监听
        viewPager.setOnPageChangeListener(this);

        title = (TitleIndicator) findViewById(R.id.title);
        title.init(tabs, viewPager);

       
        try {
            if (0 > defaultTab || defaultTab >= tabs.size()) {
                Log.v("default", String.valueOf(defaultTab));
                throw new IndexOutOfBoundsException();
            } else {

                title.setDefaultTab(defaultTab);
                viewPager.setCurrentItem(defaultTab);
            }

        } catch (NullPointerException e) {
            throw e;

        }

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        title.onScroll((viewPager.getWidth() + viewPager.getPageMargin()) * position + positionOffsetPixels);
    }

    @Override
    public void onPageSelected(int position) {
        title.setCurrentTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        
    }

    public void setDefaultTab(int index) {
        this.defaultTab = index;
    }
    

    protected abstract void setTabsAndAdapter();
}
