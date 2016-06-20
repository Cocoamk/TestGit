package com.example.dllo.baidumusic.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.SongDetailAdapter;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.bean.SongDetailBean;
import com.example.dllo.baidumusic.bean.SongPlayBean;
import com.example.dllo.baidumusic.event.PlaySongEventBus;
import com.example.dllo.baidumusic.event.PostAllSongAuthorEventBus;
import com.example.dllo.baidumusic.event.PostAllSongNameEventBus;
import com.example.dllo.baidumusic.event.PostSongIdEventBus;
import com.example.dllo.baidumusic.event.SongNameAndAuthorEventBus;
import com.example.dllo.baidumusic.event.SongPositionEventBus;
import com.example.dllo.baidumusic.event.SongsUrlEventBus;
import com.example.dllo.baidumusic.service.SongPlayService;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/5/28.
 */
public class SongDetailActivity extends BaseActivity implements View.OnClickListener {
    private SongDetailAdapter songDetailAdapter;
    private ListView listView;
    private ArrayList<SongDetailBean.ContentBean> songDetailBeen;
    private SongDetailBean bean;
    private ImageView imageViewBigPic, imagSongPlayHead;
    private TextView tvSongDetailTitle, getTvSongDetailTag, tvDesc, tvSongsName, tvSongsAuthor;
    private Intent serviceIntent;
    private ServiceConnection connection;
    private SongPlayService.SongBinder songBinder;
    private Button btnDetail, btnNext;
    private CheckBox pauseOrPlay;
    private List<String> songNameList;
    private List<String> songAuthorList;
    private List<String> songIdList;
    private List<String> songPlayUrls;
    private PopupWindow popupWindow;
    private String author, title;


    @Override
    protected int getLayout() {
        return R.layout.activity_song_detail;
    }


    @Override
    protected void initView() {
        btnDetail = (Button) findViewById(R.id.btn_detail_list);
        pauseOrPlay = (CheckBox) findViewById(R.id.btn_pause);
        btnNext = (Button) findViewById(R.id.btn_song_detail_next);
        listView = (ListView) findViewById(R.id.listView_songDetail);
        songDetailAdapter = new SongDetailAdapter(this);
        imageViewBigPic = (ImageView) findViewById(R.id.img_new_songList);
        imagSongPlayHead = (ImageView) findViewById(R.id.img_song_detail_play_head);
        tvSongDetailTitle = (TextView) findViewById(R.id.tv_song_detail_title);
        getTvSongDetailTag = (TextView) findViewById(R.id.tv_song_detail_tag);
        tvDesc = (TextView) findViewById(R.id.tv_desc);
        tvSongsName = (TextView) findViewById(R.id.tv_songs_name);
        tvSongsAuthor = (TextView) findViewById(R.id.tv_songs_author);
        listView.setAdapter(songDetailAdapter);
        imagSongPlayHead.setOnClickListener(this);
        btnDetail.setOnClickListener(this);
        pauseOrPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        EventBus.getDefault().register(this);
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

        songDetailBeen = new ArrayList<>();
        //请求网络数据
        Intent intent = getIntent();
        String a = intent.getStringExtra("list");
        String url = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy." +
                "gedanInfo&from=ios&listid=" + a + "&version=5.2.3&from=ios&channel=appstore";
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
               // Log.d("_________-----", response);
                Gson gson = new Gson();
                bean = gson.fromJson(response, SongDetailBean.class);
                //获取全部的SongName
                songNameList = new ArrayList<>();
                for (int i = 0; i < bean.getContent().size(); i++) {
                    String songEveryName = bean.getContent().get(i).getTitle();
                    songNameList.add(songEveryName);
                }
                // Log.d("+++++++++++++", "songNameList:" + songNameList);
                //获取全部的Author
                songAuthorList = new ArrayList<>();
                for (int i = 0; i < bean.getContent().size(); i++) {
                    String songEveryAuthor = bean.getContent().get(i).getAuthor();
                    songAuthorList.add(songEveryAuthor);
                }
                // Log.d("##########", "songAuthorList:" + songAuthorList);
                //获取全部Id
                songIdList = new ArrayList<>();
                for (int i = 0; i < bean.getContent().size(); i++) {
                    String songEveryId = bean.getContent().get(i).getSong_id();
                    songIdList.add(songEveryId);
                }

                Picasso.with(SongDetailActivity.this).load(bean.getPic_300()).fit().into(imageViewBigPic);
                tvSongDetailTitle.setText(bean.getTitle());
                getTvSongDetailTag.setText(bean.getTag());
                tvDesc.setText(bean.getDesc());
                songDetailAdapter.setSongDetailBeen(bean.getContent());

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
        //listView的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //传歌的ID
                EventBus.getDefault().post(new PlaySongEventBus(bean.getContent().get(position).getSong_id()));
                //传当前点击的歌的位置
                EventBus.getDefault().post(new SongPositionEventBus(position));
                //把URL的集合传过去 歌单
                EventBus.getDefault().post(new SongsUrlEventBus(songPlayUrls));
                //获取歌曲的信息
                EventBus.getDefault().post(new PostAllSongNameEventBus(songNameList));
                EventBus.getDefault().post(new PostAllSongAuthorEventBus(songAuthorList));
                EventBus.getDefault().post(new PostSongIdEventBus(songIdList));
            }
        });
    }
    //获取所有歌的歌名和歌手
    @Subscribe
    public void getSongNameAndAuthor(SongNameAndAuthorEventBus songNameAndAuthorEventBus) {
        title = songNameAndAuthorEventBus.getTitle();
        author = songNameAndAuthorEventBus.getAuthor();
        tvSongsName.setText(title);
        tvSongsAuthor.setText(author);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_detail_list:
                if (popupWindow == null || !popupWindow.isShowing()) {
                    initPopupWindow();
                } else {
                    popupWindow.dismiss();
                }
                break;
            case R.id.btn_pause:
                songBinder.playOrPause();
                break;
            case R.id.btn_song_detail_next:
                tvSongsName.setText(songBinder.getNowSongName());
                tvSongsAuthor.setText(songBinder.getNowSongAuthor());
                songBinder.next();
                break;
            case R.id.img_song_detail_play_head:
                Intent intent = new Intent(SongDetailActivity.this, SongPlayActivity.class);
                startActivity(intent);
                break;

        }
    }

    private void initPopupWindow() {
        popupWindow = new PopupWindow(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        View popupView = LayoutInflater.from(this).inflate(R.layout.popuwindow_content, null);

        popupWindow.setContentView(popupView);
        popupWindow.showAtLocation(popupView, Gravity.NO_GRAVITY, 0, 300);
    }
//取消注册
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}

