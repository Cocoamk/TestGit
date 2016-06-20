package com.example.dllo.baidumusic.event;

/**
 * Created by dllo on 16/6/14.
 * 歌曲的进度信息
 */
public class MusicSeekEvent {
   private long currentPosition;
   private long duration;

    public long getCurrentPosition() {
        return currentPosition;
    }

    public long getDuration() {
        return duration;
    }

    public void setCurrentPosition(long currentPosition) {
        this.currentPosition = currentPosition;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
