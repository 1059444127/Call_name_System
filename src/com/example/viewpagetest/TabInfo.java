package com.example.viewpagetest;

import com.example.call_name_system.R;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * //用于获取到子标题的布局内容，需要的话可以添加ImageView.
 */
public class TabInfo {

    private int      id;
    private String   name;
    private Fragment fragment;

    private View     tab;
    private TextView tv;

    public TabInfo(int id, String name, Fragment fragment) {

        this.name = name;
        this.id = id;
        this.fragment = fragment;
    }

    public int getId() {
        return id;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public View getTab(LayoutInflater inflater, ViewGroup root) {

        tab = inflater.inflate(R.layout.tab_item, root, false);
        tv = (TextView) tab.findViewById(R.id.tab_name);

        tv.setText(name);
        tab.setId(id);
        return tab;
    }

}
