package com.example.dllo.baidumusic.fragment;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.activity.AllSingerActivity;
import com.example.dllo.baidumusic.activity.SongClassifyActivity;
import com.example.dllo.baidumusic.activity.SongDetailActivity;
import com.example.dllo.baidumusic.activity.TodayRecommendActivity;
import com.example.dllo.baidumusic.adapter.RecommendImageAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.bean.RecommendBannersBean;
import com.example.dllo.baidumusic.bean.SongRecommendBean;
import com.example.dllo.baidumusic.event.ChangeViewPagerEventBus;
import com.example.dllo.baidumusic.net.NetListener;
import com.example.dllo.baidumusic.net.NetTool;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import org.greenrobot.eventbus.EventBus;
import java.util.ArrayList;


/**
 * Created by dllo on 16/5/19.
 */
public class RecommendFragment extends BaseFragment implements View.OnClickListener {
    private ViewPager viewPager;
    private RecommendImageAdapter recommendImageAdapter;
    private Handler handler;
    private boolean threadAlive = true;
    private boolean userTouch = false;
    private int sleepTick;
    private ArrayList<String>imgUrls;
    private String imageUrl = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.plaza.getFocusPic&format=json&from=ios&version=5.2.3&from=ios&channel=appstore";
    private ImageView imagUpLeft,imagUpMiddle,imageUpRight,imagDownLeft;
    private TextView tvUpLeft,tvUpMiddle,tvUpRight,tvDownLeft;
    private LinearLayout linearLayoutMore;
    private SongRecommendBean bean;
    private RelativeLayout rlSongClassify,rlAllSinger,rlTodayRecommend;
    @Override
    public int setLayout() {

        return R.layout.fragment_recommend;
    }

    @Override
    public void initView(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.recommend_ViewPager);
        imagUpLeft = (ImageView) view.findViewById(R.id.img_recommendSongUpLeft);
        imagUpMiddle = (ImageView) view.findViewById(R.id.img_recommendSongUpMiddle);
        imageUpRight = (ImageView) view.findViewById(R.id.img_recommendSongUpRight);
        imagDownLeft = (ImageView) view.findViewById(R.id.img_recommendSongDownLeft);
        tvUpLeft = (TextView) view.findViewById(R.id.tv_recommendSongUpLeft);
        tvUpMiddle = (TextView) view.findViewById(R.id.tv_recommendSongUpMiddle);
        tvUpRight = (TextView) view.findViewById(R.id.tv_recommendSongUpRight);
        tvDownLeft = (TextView) view.findViewById(R.id.tv_recommendSongDownLeft);
        linearLayoutMore = (LinearLayout) view.findViewById(R.id.ll_more);
        rlSongClassify = (RelativeLayout) view.findViewById(R.id.rl_song_classify);
        rlAllSinger = (RelativeLayout) view.findViewById(R.id.rl_all_singer);
        rlTodayRecommend = (RelativeLayout) view.findViewById(R.id.rl_today_recommend);
        rlSongClassify.setOnClickListener(this);
        rlAllSinger.setOnClickListener(this);
        rlTodayRecommend.setOnClickListener(this);
        linearLayoutMore.setOnClickListener(this);
        imagUpLeft.setOnClickListener(this);
        imagUpMiddle.setOnClickListener(this);
        imageUpRight.setOnClickListener(this);
        imagDownLeft.setOnClickListener(this);


    }
    //让线程走完销毁
    @Override
    public void onDestroy() {
        threadAlive = false;
        super.onDestroy();

    }
    @Override
    protected void initData() {

        imgUrls = null;
        recommendImageAdapter = new RecommendImageAdapter();
       //请求轮播图
        RequestQueue queue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(imageUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
               // Log.d("RecommendFragment--->", response);
                RecommendBannersBean bean = gson.fromJson(response,RecommendBannersBean.class);
                imgUrls=new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    imgUrls.add(bean.getPic().get(i).getRandpic());
                 //   Log.d("================", "imgUrls:" + imgUrls);
                }
                recommendImageAdapter.setImgUrls(imgUrls);
                viewPager.setAdapter(recommendImageAdapter);
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
        NetTool netTool = new NetTool();
        netTool.getMusicRecommend(new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
               // Log.d("RecommendFragment------", result);
              bean = gson.fromJson(result, SongRecommendBean.class);
               Picasso.with(context).load(bean.getContent().get(0).getPic_300()).fit().into(imagUpLeft);
               Picasso.with(context).load(bean.getContent().get(1).getPic_300()).fit().into(imagUpMiddle);
               Picasso.with(context).load(bean.getContent().get(2).getPic_300()).fit().into(imageUpRight);
               Picasso.with(context).load(bean.getContent().get(3).getPic_300()).fit().into(imagDownLeft);
                tvUpLeft.setText(bean.getContent().get(0).getTitle());
                tvUpMiddle.setText(bean.getContent().get(1).getTitle());
                tvUpRight.setText(bean.getContent().get(2).getTitle());
                tvDownLeft.setText(bean.getContent().get(3).getTitle());
                bean.getContent();

            }
            @Override
            public void onFailed(VolleyError error) {

            }
        });
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.ll_more:
       EventBus.getDefault().post(new ChangeViewPagerEventBus(2));
                break;
            case R.id.img_recommendSongUpLeft:
        Intent intent = new Intent(context, SongDetailActivity.class);
                intent.putExtra("list",bean.getContent().get(0).getListid());
        startActivity(intent);
                break;
            case R.id.img_recommendSongUpMiddle:
                Intent intent1 = new Intent(context, SongDetailActivity.class);
                intent1.putExtra("list",bean.getContent().get(1).getListid());
                startActivity(intent1);
                break;
            case R.id.img_recommendSongUpRight:
                Intent intent2 = new Intent(context, SongDetailActivity.class);
                intent2.putExtra("list",bean.getContent().get(2).getListid());
                startActivity(intent2);
                break;
            case R.id.img_recommendSongDownLeft:
                Intent intent3 = new Intent(context, SongDetailActivity.class);
                intent3.putExtra("list",bean.getContent().get(3).getListid());
                startActivity(intent3);
                break;
            case R.id.rl_song_classify:
                Intent intentSongClassify = new Intent(context, SongClassifyActivity.class);
                startActivity(intentSongClassify);
                break;
            case R.id.rl_all_singer:
                Intent intentAllSinger = new Intent(context, AllSingerActivity.class);
                startActivity(intentAllSinger);
                break;
            case R.id.rl_today_recommend:
                Intent intentTodayRecommend = new Intent(context, TodayRecommendActivity.class);
                startActivity(intentTodayRecommend);
                break;

        }

    }




}
