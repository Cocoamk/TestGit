package com.example.dllo.baidumusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dllo.baidumusic.fragment.MusicLibraryFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class MusicLibraryAdapter extends FragmentPagerAdapter {
    private String[] musicLibraryTitles={"推荐","排行","歌单","电台","MV"};
    private ArrayList<Fragment> fragments;

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments = fragments;
    }

    public MusicLibraryAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments!=null&&fragments.size()>0?fragments.get(position):null;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return musicLibraryTitles[position];
    }
}
