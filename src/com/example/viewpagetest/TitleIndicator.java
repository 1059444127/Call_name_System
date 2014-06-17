package com.example.viewpagetest;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class TitleIndicator extends LinearLayout implements OnClickListener {

    private int            currentTab      = 0;          // 褰撳墠椤甸潰
    private int            currentLocationlX;            // 婊氬姩璺濈
    private int            totalTabs       = 0;          // 鎬婚〉闈㈡暟鐩�
    private int            mWidth;                       // 姝ら�椤瑰崱鐨勫搴�
    private int            mHeight;                      // 姝ら�椤瑰崱鐨勯珮搴�
    private int            cursorWidth;                  // 娓告爣瀹藉害
    private int            cursorHeight;                 // 娓告爣楂樺害
    private Path           cursorPath      = new Path(); // 娓告爣杞ㄨ抗

    private Path           bottomLine      = new Path();
    private boolean        initialize      = false;      // 鏄惁鍒濆鍖�
    private Paint          paint           = new Paint(); // 鐢荤瑪
    private boolean        needPortLine    = true;       // 鏃跺�闇�搴曢儴鍒嗗壊绾�
    private ViewPager      viewPager;                    // 閫夐」鍗℃墍渚濊禆鐨剉iewpager
    private LayoutInflater inflater;
    private int            bottomLineThick = 2;

    public TitleIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = ((Activity) context).getLayoutInflater();

        // TODO Auto-generated constructor stub

    }

    public void init(List<TabInfo> tabs, ViewPager viewPager) {
        this.viewPager = viewPager;

        setWillNotDraw(false);// // 调用invalidate()时强制调用onDraw()方法

        totalTabs = tabs.size();

        // 设置标题的每一个Tab的位置属性
        for (int i = 0; i < totalTabs; i++) {
            View view = tabs.get(i).getTab(inflater, this);
            LinearLayout.LayoutParams lP = (LinearLayout.LayoutParams) view.getLayoutParams();
            lP.gravity = Gravity.CENTER_VERTICAL;
            view.setOnClickListener(this);
            addView(view);
        }
        setCurrentTab(currentTab);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);

        if (!initialize) {
            initTab(canvas);
        }

        float scroll_x = 0;
        if (totalTabs != 0) {
          
            scroll_x = (currentLocationlX - ((currentTab) * (mWidth + viewPager.getPageMargin()))) / totalTabs;
        } else {
            scroll_x = currentLocationlX;
        }

        cursorPath.rewind();

        float left_x = currentTab * cursorWidth + scroll_x;
        float right_x = (currentTab + 1) * cursorWidth + scroll_x;
        float top_y = mHeight - cursorHeight - 2;
        float bottom_y = mHeight - 2;

        cursorPath.moveTo(left_x, top_y); 
        cursorPath.lineTo(right_x, top_y); 
        cursorPath.lineTo(right_x, bottom_y);
        cursorPath.lineTo(left_x, bottom_y);
        cursorPath.close();

        canvas.drawPath(cursorPath, paint);

        // 搴曢儴鍒嗗壊绾跨嚎鏉�
        if (needPortLine && !initialize) {

            bottomLine.moveTo(0, mHeight);
            bottomLine.lineTo(0, mHeight - bottomLineThick);
            bottomLine.lineTo(mWidth, mHeight - bottomLineThick);
            bottomLine.lineTo(mWidth, mHeight);
            bottomLine.close();
        }
        canvas.drawPath(bottomLine, paint);

        initialize = true;
    }

    // 鍒濆鍖栨閫夐」鍗�
    private void initTab(Canvas canvas) {

        mWidth = getWidth();
        mHeight = getHeight();
        if (totalTabs != 0) cursorWidth = mWidth / totalTabs;
        else cursorWidth = mWidth;
        cursorHeight = mHeight / 10;
        paint.setStyle(Paint.Style.FILL);
        paint.setARGB(255, 0x01, 0xA9, 0xDB);
    }

    // 婊氬姩
    public void onScroll(int currentLocationlX) {
        this.currentLocationlX = currentLocationlX;
        invalidate();
    }

    public void setCurrentTab(int i) {

      
        View view = getChildAt(currentTab);
        view.setSelected(false);

        currentTab = i;
       
        view = getChildAt(currentTab);
        view.setSelected(true);
        invalidate();

        if (viewPager.getCurrentItem() != i) viewPager.setCurrentItem(i);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        setCurrentTab(v.getId());

    }

    public void setNeedPortLine(boolean needPortLine) {
        this.needPortLine = needPortLine;
    }

    public void setDefaultTab(int index) {
        this.currentTab = index;
    }
}
