package com.example.dllo.baidumusic.activity;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
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
import com.example.dllo.baidumusic.adapter.RankingPlayAdapter;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.bean.RankingPlayBean;
import com.example.dllo.baidumusic.event.PlaySongEventBus;
import com.example.dllo.baidumusic.service.SongPlayService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/1.
 */
public class RankingPlayActivity extends BaseActivity {
    private ListView listView;
    private RankingPlayAdapter rankingPlayAdapter;
    private ArrayList<RankingPlayBean.SongListBean> songListBeen;
    private ImageView imgNewSongPic;
    private RankingPlayBean rankingPlayBean;
    private Intent serviceIntent;
    private ServiceConnection connection;
    private SongPlayService.SongBinder songBinder;
    private Context context;


    @Override
    protected int getLayout() {
        return R.layout.activity_rankinglist_play;
    }

    @Override
    protected void initView() {
        listView = (ListView) findViewById(R.id.listView_ranking_play);
        rankingPlayAdapter = new RankingPlayAdapter(this);
        imgNewSongPic = (ImageView) findViewById(R.id.img_ranking_play_pic);
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
        //获取网络数据
        songListBeen = new ArrayList<>();
        Intent intent = getIntent();
        int b = intent.getIntExtra("type", 0);
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type="
                + b + "&format=json&offset=0&size=50&from=ios&fields=title,song_id," +
                "author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_" +
                "uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                rankingPlayBean = gson.fromJson(response, RankingPlayBean.class);
                Picasso.with(context).load(rankingPlayBean.getSong_list().get(2).getPic_big()).fit().into(imgNewSongPic);
                rankingPlayAdapter.setRankingPlayBeen(rankingPlayBean.getSong_list());
                listView.setAdapter(rankingPlayAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
        //listViewd的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventBus.getDefault().post(new PlaySongEventBus(rankingPlayBean.getSong_list().get(position).getSong_id()));
            }
        });
    }
}
