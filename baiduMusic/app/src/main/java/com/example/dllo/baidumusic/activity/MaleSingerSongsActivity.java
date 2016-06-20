package com.example.dllo.baidumusic.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.MaleSingerSongsAdapter;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.bean.MaleSingerSongsBean;
import com.example.dllo.baidumusic.bean.SongDetailBean;
import com.example.dllo.baidumusic.event.PlaySongEventBus;
import com.example.dllo.baidumusic.service.SongPlayService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/6.
 */
public class MaleSingerSongsActivity extends BaseActivity {
    private ListView maleSingerSongsListView;
    private MaleSingerSongsAdapter maleSingerSongsAdapter;
    private ArrayList<MaleSingerSongsBean.SonglistBean> maleSingerSongsBeen;
    private MaleSingerSongsBean maleSingerSongsBean;
    private ImageView imageViewBigHead;
    private Intent serviceIntent;
    private ServiceConnection connection;
    private SongPlayService.SongBinder songBinder;
     @Override
    protected int getLayout() {
        return R.layout.activity_male_singer_songs;
    }

    @Override
    protected void initView() {
        maleSingerSongsListView = (ListView) findViewById(R.id.listView_male_singer_songs);
        imageViewBigHead = (ImageView) findViewById(R.id.img_chinese_male_play_pic);
    }

    @Override
    protected void initData() {
        //绑定服务
        serviceIntent = new Intent(this, SongPlayService.class);
        startService(serviceIntent);
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                songBinder = (SongPlayService.SongBinder) service;
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
            }
        };
        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
         maleSingerSongsBeen = new ArrayList<>();
        maleSingerSongsAdapter = new MaleSingerSongsAdapter(this);
        Intent intent = getIntent();
        final String u = intent.getStringExtra("aaa");
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.artist.getSongList&format=json&tinguid=" + u + "&artistid(null)&limits=30&order=2&offset=0&version=5.2.5&from=ios&channel=appstore";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("MaleSingerSongs--------", response);
                Gson gson = new Gson();
              maleSingerSongsBean = gson.fromJson(response, MaleSingerSongsBean.class);
                Picasso.with(MaleSingerSongsActivity.this).load(maleSingerSongsBean.getSonglist().get(0).getPic_big()).fit().into(imageViewBigHead);

                maleSingerSongsAdapter.setMaleSingerSongsBeen(maleSingerSongsBean.getSonglist());
                maleSingerSongsListView.setAdapter(maleSingerSongsAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("fail", "失败");
            }
        });
        queue.add(stringRequest);
        maleSingerSongsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new PlaySongEventBus(maleSingerSongsBean.getSonglist().get(position).getSong_id()));
            }
        });
    }

}
