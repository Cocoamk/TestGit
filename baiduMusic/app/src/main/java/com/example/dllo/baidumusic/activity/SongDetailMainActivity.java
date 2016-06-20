package com.example.dllo.baidumusic.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseActivity;


/**
 * Created by dllo on 16/6/8.
 */
public class SongDetailMainActivity extends BaseActivity implements View.OnClickListener {
private ImageView imgSongPlayHead;
    @Override
    protected int getLayout() {
        return R.layout.activity_song_detail_main;
    }

    @Override
    protected void initView() {
imgSongPlayHead = (ImageView) findViewById(R.id.img_song_play_head);
        imgSongPlayHead.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SongDetailMainActivity.this,SongPlayActivity.class);
        startActivity(intent);
    }
}
