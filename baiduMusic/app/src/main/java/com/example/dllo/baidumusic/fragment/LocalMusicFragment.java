package com.example.dllo.baidumusic.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.activity.MainActivity;
import com.example.dllo.baidumusic.adapter.LocalMusicAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;

import java.util.ArrayList;


/**
 * Created by dllo on 16/5/25.
 */
public class LocalMusicFragment extends BaseFragment implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LocalMusicAdapter localMusicAdapter;
    private ArrayList<Fragment> fragments;
    private Button btnLocalMusicReturn;

    @Override
    public int setLayout() {
        return R.layout.fragment_local_music;
    }

    @Override
    public void initView(View view) {
        tabLayout = (TabLayout) view.findViewById(R.id.localMusic_tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.localMusic_viewPager);
    initFragment();
    localMusicAdapter = new LocalMusicAdapter(getChildFragmentManager());
        localMusicAdapter.setFragments(fragments);
        viewPager.setAdapter(localMusicAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
        btnLocalMusicReturn = (Button) view.findViewById(R.id.btn_local_music_finish);
        btnLocalMusicReturn.setOnClickListener(this);

    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new SongFragment());
        fragments.add(new FileFragment());
        fragments.add(new SingerFragment());
        fragments.add(new CDFragment());
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_local_music_finish:
                break;
        }
    }
}
