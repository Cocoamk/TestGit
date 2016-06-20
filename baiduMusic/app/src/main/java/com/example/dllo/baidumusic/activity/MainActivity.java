package com.example.dllo.baidumusic.activity;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.example.dllo.baidumusic.adapter.MainPageAdapter;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.fragment.DirectFragment;
import com.example.dllo.baidumusic.fragment.KMusicFragment;
import com.example.dllo.baidumusic.fragment.MusicLibraryFragment;
import com.example.dllo.baidumusic.fragment.MyFragment;
import com.example.dllo.baidumusic.fragment.PersonalFragment;
import com.example.dllo.baidumusic.fragment.SearchFragment;

import java.util.ArrayList;


public class MainActivity extends BaseActivity implements View.OnClickListener {
private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainPageAdapter adapter;
    private ArrayList<Fragment> fragments;
    private LinearLayout llSongPlay;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        tabLayout = (TabLayout) findViewById(R.id.main_tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewPage);
        initFragment();
        adapter = new MainPageAdapter(getSupportFragmentManager());
        adapter.setFragments(fragments);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.TRANSPARENT);
        tabLayout.getTabAt(4).setIcon(R.mipmap.bt_titlebar_new_search_normal);
        tabLayout.getTabAt(5).setIcon(R.mipmap.bt_sceneplay_singer);
        llSongPlay = (LinearLayout) findViewById(R.id.ll_song_play);
        llSongPlay.setOnClickListener(this);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new MyFragment());
        fragments.add(new MusicLibraryFragment());
        fragments.add(new KMusicFragment());
        fragments.add(new DirectFragment());
        fragments.add(new SearchFragment());
        fragments.add(new PersonalFragment());

    }
    //封装Fragment之间跳转的方法
    private void fragmentReplace(int id,Fragment fragment) {
        FragmentManager m = getSupportFragmentManager();
        FragmentTransaction fm = m.beginTransaction();
        fm.replace(id,fragment).addToBackStack(null);//加到返回栈
        fm.commit();
    }


    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this,SongPlayActivity.class);
        startActivity(intent);
    }
}
