package com.example.dllo.baidumusic.event;

/**
 * Created by dllo on 16/6/14.
 */
public class SeekBarProgressEventBus {
    int current;

    public SeekBarProgressEventBus(int current) {
        this.current = current;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
