package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.widget.Adapter;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/18.
 */
public class MainPageAdapter extends FragmentPagerAdapter {
   private ArrayList<Fragment> fragments;
    private String[] titles= { "我的","乐库","K歌","直播","",""};

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MainPageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public MainPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments!= null&&fragments.size()>0?fragments.get(position):null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


}
