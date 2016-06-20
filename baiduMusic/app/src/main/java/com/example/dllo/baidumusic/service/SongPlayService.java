package com.example.dllo.baidumusic.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dllo.baidumusic.bean.SongPlayBean;
import com.example.dllo.baidumusic.event.MusicSeekEvent;
import com.example.dllo.baidumusic.event.PlaySongEventBus;
import com.example.dllo.baidumusic.event.PostAllSongAuthorEventBus;
import com.example.dllo.baidumusic.event.PostAllSongNameEventBus;
import com.example.dllo.baidumusic.event.PostSongIdEventBus;
import com.example.dllo.baidumusic.event.SeekBarProgressEventBus;
import com.example.dllo.baidumusic.event.SongNameAndAuthorEventBus;
import com.example.dllo.baidumusic.event.SongPositionEventBus;
import com.example.dllo.baidumusic.net.URLValues;
import com.example.dllo.baidumusic.net.VolleySingleton;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.util.List;
import java.util.Random;

/**
 * Created by dllo on 16/6/1.
 */
public class SongPlayService extends Service {
    private String songId;
    private SongPlayBean songPlayBean;
    private MediaPlayer mediaPlayer;
    private SongBinder binder = new SongBinder();
    private RequestQueue queue;
    private int position;
    private List<String> songPlayUrls;
    private List<String> songNameList;
    private List<String> songAuthorList;
    private List<String> songIdList;
    private SeekBarProgress seekBarProgress;

    @Subscribe
    public void getSongPlay(PlaySongEventBus eventVBus) {
        songId = eventVBus.getSongId();
        RequestQueue queue = Volley.newRequestQueue(this);
        String songPlayUrl = URLValues.SONG_PLAY + songId + URLValues.SONG_PLAY_END;
        StringRequest stringRequest = new StringRequest(songPlayUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                songPlayBean = gson.fromJson(response.substring(1, response.length() - 2), SongPlayBean.class);
                play();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        queue.add(stringRequest);
    }

    //直接播放选中的item
    public void play() {
        mediaPlayer.reset();
        // mediaPlayer.release();
        try {
            mediaPlayer.setDataSource(songPlayBean.getBitrate().getFile_link());
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();

                    EventBus.getDefault().post(new SongNameAndAuthorEventBus(songNameList.get(position - 1), songAuthorList.get(position - 1)));

                }
            });
            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        queue = VolleySingleton.getInstance().getRequestQueue();
        EventBus.getDefault().register(this);
        //动态注册广播
        seekBarProgress = new SeekBarProgress();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("Action_seekBar");
        registerReceiver(seekBarProgress, intentFilter);
        mediaPlayer = new MediaPlayer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    while (mediaPlayer.isPlaying()) {
                        //发送歌曲的进度信息
                        MusicSeekEvent musicSeekEvent = new MusicSeekEvent();
                        musicSeekEvent.setCurrentPosition(mediaPlayer.getCurrentPosition());
                        musicSeekEvent.setDuration(mediaPlayer.getDuration());
                        EventBus.getDefault().post(musicSeekEvent);
                        try {
                            Thread.sleep(200);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void sendNameAndAuthor() {
        String songName = songNameList.get(position);
        String author = songAuthorList.get(position);
        SongNameAndAuthorEventBus songNameAndAuthorEventBus = new SongNameAndAuthorEventBus(songName, author);
        EventBus.getDefault().post(songNameAndAuthorEventBus);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public class SongBinder extends Binder {
        public void playOrPause() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            } else {
                if (mediaPlayer != null) {
                    mediaPlayer.start();
                    //开始播放
                    sendNameAndAuthor();
                }
            }


        }

        public String getNowSongName() {
            return songNameList.get(position);
        }

        public String getNowSongAuthor() {
            return songAuthorList.get(position);
        }

        //播放上一首
        public void before() {

            if (position == 0) {

                position = songPlayUrls.size() + 1;
            } else {
                position = position - 1;
            }
            play();
        }

        //调用播放下一曲的方法
        public void next() {
            position = position + 1;
            playNext();
        }
    }

    //获取拖动后的歌曲信息
    public void getCurrent(SeekBarProgressEventBus seekBarProgressEventBus) {
        seekBarProgressEventBus.getCurrent();
    }

    //获取所有歌的URL的集合
    @Subscribe
    public void playPosition(PostSongIdEventBus songIdEventBus) {
        songPlayUrls = songIdEventBus.getSongId();
    }

    //播放下一曲
    @Subscribe
    public void getSongPosition(SongPositionEventBus songPositionEventBus) {
        position = songPositionEventBus.getPosition();
        position = position + 1;
    }

    private void playNext() {
        //Log.d("SongPlayService", "position:" + position);
        //循环读取 ,防止数组越界
        StringRequest stringRequest = new StringRequest(URLValues.SONG_PLAY + songPlayUrls.get((position) % songPlayUrls.size()) + URLValues.SONG_PLAY_END, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                songPlayBean = gson.fromJson(response.substring(1, response.length() - 2), SongPlayBean.class);
                mediaPlayer.reset();
                try {
                    mediaPlayer.setDataSource(songPlayBean.getBitrate().getFile_link());
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                    Intent nextIntent = new Intent("com.nick.myBaiDuMusic.next");
                    nextIntent.putExtra("broadNextState", mediaPlayer.isPlaying());
                    sendNameAndAuthor();//发送一次新的歌曲名和歌手名
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);
    }

    //获取所有歌的歌名
    @Subscribe
    public void getAllSongName(PostAllSongNameEventBus postAllSongNameEventBus) {
        songNameList = postAllSongNameEventBus.getTitle();
    }

    @Subscribe
    public void getAllSongsAuthor(PostAllSongAuthorEventBus postAllSongAuthorEventBus) {
        songAuthorList = postAllSongAuthorEventBus.getAuthor();
    }

    //接收广播
    class SeekBarProgress extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            int progress = intent.getIntExtra("seekBar", 0);
            mediaPlayer.seekTo(progress);
            Log.d("kkkkkkk", "progress:" + progress);
        }
    }
}

