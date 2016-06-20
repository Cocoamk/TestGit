package com.example.dllo.baidumusic.fragment;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;


/**
 * Created by dllo on 16/5/18.
 */
public class MyFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout localMusic,recentlyPlay,loadManager,myKSong,myDefault,newSongList;
    private RelativeLayout myCollectSong;
    @Override
    public int setLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView(View view) {
        localMusic = (LinearLayout) view.findViewById(R.id.ll_local_music);
   recentlyPlay = (LinearLayout) view.findViewById(R.id.ll_recently_play);
        loadManager = (LinearLayout) view.findViewById(R.id.ll_load_manager);
        myKSong = (LinearLayout) view.findViewById(R.id.ll_my_ksong);
        myDefault = (LinearLayout) view.findViewById(R.id.ll_my_default_songList);
        myCollectSong= (RelativeLayout) view.findViewById(R.id.rl_my_collect_songs);
        newSongList = (LinearLayout) view.findViewById(R.id.ll_new_songList);
        newSongList.setOnClickListener(this);
        recentlyPlay.setOnClickListener(this);
        localMusic.setOnClickListener(this);
        loadManager.setOnClickListener(this);
        myKSong.setOnClickListener(this);
        myDefault.setOnClickListener(this);
        myCollectSong.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.ll_local_music:
                fragmentReplace(R.id.fragmentLayout_mine,new LocalMusicFragment());
                break;
            case R.id.ll_recently_play:

                fragmentReplace(R.id.fragmentLayout_mine,new RecentlyPlayFragment());
                break;
            case R.id.ll_load_manager:
             fragmentReplace(R.id.fragmentLayout_mine,new LoadManagerFragment());

                break;
            case R.id.ll_my_ksong:
                fragmentReplace(R.id.fragmentLayout_mine,new MyKSongFragment());
            break;
            case R.id.rl_my_collect_songs:
                fragmentReplace(R.id.fragmentLayout_mine,new MyCollectSongListFragment());
                break;
            case R.id.ll_my_default_songList:
                fragmentReplace(R.id.fragmentLayout_mine,new MyDefaultSongListFragment());
                break;
            case R.id.ll_new_songList:
                setShowCustomDialog();
                break;

        }


    }
    public void setShowCustomDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("新建歌单");
        View view = LayoutInflater.from(context).inflate(R.layout.newsong_custom_dialog,null);
        final EditText etNewSong = (EditText) view.findViewById(R.id.et_new_songList);
        alert.setView(view);
        alert.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String songList = etNewSong.getText().toString();

            }
        });
        alert.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
//Fragment之间跳转的方法
    private void fragmentReplace(int id,Fragment fragment) {
        FragmentManager m = getActivity().getSupportFragmentManager();
        FragmentTransaction fm = m.beginTransaction();
        fm.replace(id,fragment).addToBackStack(null);//加到返回栈
        fm.commit();
    }


}
