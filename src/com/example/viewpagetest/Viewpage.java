package com.example.viewpagetest;

import java.util.ArrayList;

import com.example.four_acticity.Four_acticity;

public class Viewpage extends PagerScrollerActivity {

    @Override
    protected void setTabsAndAdapter() {
        // TODO Auto-generated method stub
        tabs = new ArrayList<TabInfo>();
        tabs.add(new TabInfo(0, "ҵ����",  new firstFragment()));
        tabs.add(new TabInfo(1, "ԤԼ��Ϣ",  new secondFragment()));
        tabs.add(new TabInfo(2, "�кŹ���",  new Four_acticity()));

        this.adapter = new FragmentsAdapter(this, getSupportFragmentManager(), tabs);

    }

}
