package com.example.dllo.baidumusic.event;

/**
 * Created by dllo on 16/6/4.
 */
public class SongPositionEventBus {
    int position;

    public SongPositionEventBus(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
