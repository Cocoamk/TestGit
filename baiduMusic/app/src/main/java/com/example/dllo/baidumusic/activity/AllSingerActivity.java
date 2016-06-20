package com.example.dllo.baidumusic.activity;
import android.content.Intent;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.AllSingerAdapter;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.bean.AllSingerBean;
import com.google.gson.Gson;
import java.util.ArrayList;
import android.os.Handler;
import android.widget.LinearLayout;

/**
 * Created by dllo on 16/6/4.
 */
public class AllSingerActivity extends BaseActivity implements View.OnClickListener {
    private ViewPager viewPager;
    private Button btnBack;
    private boolean threadAlive = true;
    private boolean userTouch = false;
    private int sleepTick;
    private AllSingerAdapter allSingerAdapter;
    public  String bannerUrls ="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.artist.getList&format=json&order=1&limit=6&offset=0&area=0&sex=0&abc=&from=ios&version=5.2.1&from=ios&channel=appstore";
    private AllSingerBean allSingerBean;
    private Handler handler;
    private LinearLayout llChineseMaleSinger;
    @Override
    protected int getLayout() {
        return R.layout.activity_all_singer;
    }

    @Override
    protected void initView() {
        btnBack = (Button) findViewById(R.id.btn_all_singer_return);
        viewPager = (ViewPager) findViewById(R.id.singer_viewPager);
        llChineseMaleSinger = (LinearLayout) findViewById(R.id.ll_chinese_male_singer);
        llChineseMaleSinger.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        allSingerAdapter = new AllSingerAdapter();
      RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(bannerUrls, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("AllSingerActivity", "-------");
               Gson gson = new Gson();
               allSingerBean = gson.fromJson(response,AllSingerBean.class);
            ArrayList<String> bannerUrls = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    bannerUrls.add(allSingerBean.getArtist().get(i).getAvatar_big());
                }
               allSingerAdapter.setBannerUrls(bannerUrls);
               viewPager.setAdapter(allSingerAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                //获取当前的位置,再将ViewPager刷新到下一页
                int current = viewPager.getCurrentItem();
                viewPager.setCurrentItem(current + 1);
                return false;
            }
        });
        //开启线程去执行轮播
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (threadAlive) {
                    //每隔5s刷新一次ViewPager的数据
                    for (sleepTick = 0; sleepTick < 5; sleepTick++) ;
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!userTouch) {
                        handler.sendEmptyMessage(0);
                    }
                }
            }
        }).start();
        //当用户点击的时候就不会再触发轮播图了,轮播图就会暂停轮播
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        //用户触摸了轮播图的时候
                        userTouch = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        //手指离开轮播图的时候
                        userTouch = false;
                        //每次当用抬起手指,就会重新开始计时
                        sleepTick = 0;
                }
                return false;
            }
        });
        queue.add(stringRequest);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_all_singer_return:
                finish();
                break;
            case R.id.ll_chinese_male_singer:
                Intent chineseMaleIntent = new Intent(this,ChineseMaleSingerActivity.class);
                startActivity(chineseMaleIntent);
                break;
        }
    }
}
