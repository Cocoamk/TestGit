package com.example.dllo.baidumusic.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.event.MusicSeekEvent;
import com.example.dllo.baidumusic.event.SongNameAndAuthorEventBus;
import com.example.dllo.baidumusic.service.SongPlayService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;



/**
 * Created by dllo on 16/5/30.
 */
public class SongPlayActivity extends BaseActivity implements View.OnClickListener {
    private TextView textViewName, textViewAuthor,tvCurrentTime,tvTotalTime;
    private CheckBox playOrPause;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer = null;
    private String songName,author;
    private ImageButton imgBtnBefore,imgBtnNext,imgBtnSongsState;
    private SongPlayService.SongBinder songBinder;
    private Intent serviceIntent;
    private ServiceConnection connection;
    private int state = 0;
    private int images[]= {R.mipmap.bt_list_button_roundplay_normal,R.mipmap.bt_list_random_normal,R.mipmap.bt_list_order_normal,
    R.mipmap.bt_list_roundsingle_normal};
    private long curTime,totalTime;
    private DateFormat dateFormat;
    @Override
    protected int getLayout() {
        return R.layout.activity_song_play;
    }

    @Override
    protected void initView() {
        imgBtnSongsState = (ImageButton) findViewById(R.id.imgBtn_songs_state);
        imgBtnBefore = (ImageButton) findViewById(R.id.imgBtn_before);
        imgBtnNext = (ImageButton) findViewById(R.id.imgBtn_next);
        textViewName = (TextView) findViewById(R.id.tv_song_name);
        textViewAuthor = (TextView) findViewById(R.id.tv_singer);
        tvCurrentTime = (TextView) findViewById(R.id.tv_current_time);
        tvTotalTime = (TextView) findViewById(R.id.tv_total_time);
        EventBus.getDefault().register(this);
        mediaPlayer = new MediaPlayer();
        findViews();
         imgBtnBefore.setOnClickListener(this);
        imgBtnNext.setOnClickListener(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMusicSeekInfo(MusicSeekEvent musicSeekEvent){
Log.d("SongPlayActivity", "musicSeekEvent.getCurrentPosition():" + musicSeekEvent.getCurrentPosition());
        seekBar.setMax((int) musicSeekEvent.getDuration());
        seekBar.setProgress((int) musicSeekEvent.getCurrentPosition());
        curTime =  musicSeekEvent.getCurrentPosition();
        totalTime = musicSeekEvent.getDuration();
        tvCurrentTime.setText(timeFormat(curTime));
        tvTotalTime.setText(timeFormat(totalTime));
        Log.d("Sssss", "curTime:" + curTime);


    }

    private void findViews() {
        playOrPause = (CheckBox) findViewById(R.id.imgBtn_play_or_pause);
        playOrPause.setOnClickListener(this);
        seekBar = (SeekBar) findViewById(R.id.song_play_seekBar);
        seekBar.setOnSeekBarChangeListener(new MySeekBar());

    }

    //获取歌名和歌手
    @Subscribe
    public void getSongNameAndAuthor(SongNameAndAuthorEventBus songNameAndAuthorEventBus) {
        author = songNameAndAuthorEventBus.getAuthor();
        songName = songNameAndAuthorEventBus.getTitle();
        textViewName.setText(songName);
        textViewAuthor.setText(author);
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

//切换播放歌曲模式
imgBtnSongsState.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        state = (state + 1) % 4;
        modelPlay(state);
       imgBtnSongsState.setImageResource(images[state]);
    }
});
    }

    private void modelPlay(int stateNum) {
 Intent intent = new Intent("ACTION_MODEL");
        intent.putExtra("state",stateNum);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imgBtn_before:
            songBinder.before();
                break;
            case R.id.imgBtn_play_or_pause:
                songBinder.playOrPause();
                break;
            case R.id.imgBtn_next:
                songBinder.next();
                break;

        }
    }

    //格式化时间
    public String timeFormat(long time) {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("mm:ss");
        }
        return dateFormat.format(time);
    }
    class MySeekBar implements SeekBar.OnSeekBarChangeListener {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if (fromUser == true){
                //发一条广播
             Intent intent = new Intent("Action_seekBar");
                intent.putExtra("seekBar",progress);
                sendBroadcast(intent);

               mediaPlayer.seekTo(progress);
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            //把歌曲的进度信息发送给服务
            int current = seekBar.getProgress();


        }
    }

}
