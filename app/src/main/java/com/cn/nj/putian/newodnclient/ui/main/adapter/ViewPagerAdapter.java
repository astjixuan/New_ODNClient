package com.cn.nj.putian.newodnclient.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public final class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = null;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public void setList(List<Fragment> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }
}
