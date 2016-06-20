package com.example.dllo.baidumusic.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.MyKSongAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/24.
 */
public class MyKSongFragment extends BaseFragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MyKSongAdapter myKSongAdapter;
    private ArrayList<Fragment> fragments;
    @Override
    public int setLayout() {
        return R.layout.fragment_my_ksong;
    }

    @Override
    public void initView(View view) {
     tabLayout = (TabLayout) view.findViewById(R.id.myKSong_tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.myKSong_viewPager);
        initFragment();
        myKSongAdapter = new MyKSongAdapter(getChildFragmentManager());
        myKSongAdapter.setFragments(fragments);
        viewPager.setAdapter(myKSongAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LocalAccompanyFragment());
        fragments.add(new LocalRecordFragment());
        fragments.add(new WorkShareFragment());
    }

    @Override
    protected void initData() {

    }
}
