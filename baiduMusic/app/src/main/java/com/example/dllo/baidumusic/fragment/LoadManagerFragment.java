package com.example.dllo.baidumusic.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.LoadManagerAdapter;
import com.example.dllo.baidumusic.adapter.LocalMusicAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/24.
 */
public class LoadManagerFragment extends BaseFragment {
    private Button btn_loadManager_return;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ArrayList<Fragment> fragments;
    private LoadManagerAdapter managerAdapter;

    @Override
    public int setLayout() {
        return R.layout.fragment_download_manager;
    }

    @Override
    public void initView(View view) {
        btn_loadManager_return = (Button) view.findViewById(R.id.btn_loadmanager_return);
        btn_loadManager_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        tabLayout = (TabLayout) view.findViewById(R.id.loadManger_tabLayout);
        viewPager = (ViewPager) view.findViewById(R.id.loadManager_viewPager);
        initFragment();
       managerAdapter = new LoadManagerAdapter(getChildFragmentManager());
        managerAdapter.setFragments(fragments);
        viewPager.setAdapter(managerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(Color.BLUE);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new LoadedFragment());
        fragments.add(new LoadingFragment());
    }

    @Override
    protected void initData() {

    }
}
