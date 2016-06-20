package com.example.dllo.baidumusic.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.KMusicAdapter;
import com.example.dllo.baidumusic.adapter.KMusicBannersAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.bean.RankingPlayBean;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/18.
 */
public class KMusicFragment extends BaseFragment {
    private RecyclerView recyclerView;
    private KMusicAdapter adapter;
    private ArrayList<RankingPlayBean> rankingPlayBeen;
    private RankingPlayBean rankingPlayBean;
    //轮播图
    private ViewPager viewPager;
    private int photos[] = {R.mipmap.wujianhao, R.mipmap.biyeji, R.mipmap.chenruoyi};
    private KMusicBannersAdapter kMusicBannersAdapter;
    private Handler handler;
    private boolean threadAlive = true;
    private boolean userTouch = false;
    private int sleepTick;

    @Override
    public int setLayout() {
        return R.layout.fragment_kmusic;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.k_recyclerView);
        adapter = new KMusicAdapter(getContext());

        //banners
        viewPager = (ViewPager) view.findViewById(R.id.kSong_ViewPager);
        kMusicBannersAdapter = new KMusicBannersAdapter(context);
        kMusicBannersAdapter.setImageViews(photos, context);
        viewPager.setAdapter(kMusicBannersAdapter);
        handler = new Handler(new Handler.Callback() {

            @Override
            public boolean handleMessage(Message msg) {
                int current = viewPager.getCurrentItem();
                viewPager.setCurrentItem(current + 1);
                return false;
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (threadAlive) {
                    for (sleepTick = 0; sleepTick < 5; sleepTick++)
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    if (!userTouch) {
                        handler.sendEmptyMessage(0);

                    }
                }
            }
        }).start();
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        userTouch = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        userTouch = false;
                        sleepTick = 0;
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        rankingPlayBeen = new ArrayList<>();
        Intent intent = new Intent();
        int b = intent.getIntExtra("type", 0);
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type="
                + b + "&format=json&offset=0&size=50&from=ios&fields=title,song_id," +
                "author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_" +
                "uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                //Log.d("KMusicFragment-----", response);
                Gson gson = new Gson();
                rankingPlayBean = gson.fromJson(response, RankingPlayBean.class);
                adapter.setRankingPlayBeen(rankingPlayBean.getSong_list());
                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(manager);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);


    }


}
