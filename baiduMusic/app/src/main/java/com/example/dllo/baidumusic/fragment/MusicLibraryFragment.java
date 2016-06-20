package com.example.dllo.baidumusic.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.MusicLibraryAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.event.ChangeViewPagerEventBus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/18.
 */
public class MusicLibraryFragment extends BaseFragment {
    private TabLayout musicLibraryTabLayout;
    private ViewPager musicLibraryViewPager;
    private ArrayList<Fragment> fragments;
    private MusicLibraryAdapter musicLibraryAdapter;
    public MusicLibraryFragment(){
        EventBus.getDefault().register(this);
    }
    @Override
    public int setLayout() {
        return R.layout.fragment_musiclibrary;
    }

    @Override
    public void initView(View view) {
        musicLibraryTabLayout = (TabLayout) view.findViewById(R.id.tablayout_musicLibrary);
        musicLibraryViewPager = (ViewPager) view.findViewById(R.id.viewPage_musicLibrary);
       initFragment();
        musicLibraryAdapter = new MusicLibraryAdapter(getChildFragmentManager());
        musicLibraryAdapter.setFragments(fragments);
        musicLibraryViewPager.setAdapter(musicLibraryAdapter);
        musicLibraryTabLayout.setupWithViewPager(musicLibraryViewPager);
        musicLibraryTabLayout.setSelectedTabIndicatorColor(Color.BLUE);

    }

    private void initFragment() {

        fragments = new ArrayList<>();
        fragments.add(new RecommendFragment());
        fragments.add(new RankingListFragment());
        fragments.add(new SongListFragment());
        fragments.add(new RadioStationFragment());
        fragments.add(new MVFragment());
    }

    @Override
    protected void initData() {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getNewPager(ChangeViewPagerEventBus eventBus){
        musicLibraryViewPager.setCurrentItem(eventBus.getCurrent());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
