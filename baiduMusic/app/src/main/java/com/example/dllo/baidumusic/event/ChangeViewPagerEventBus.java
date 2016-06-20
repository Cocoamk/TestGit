package com.example.dllo.baidumusic.event;

import android.os.Handler;

/**
 * Created by dllo on 16/5/28.
 */
public class ChangeViewPagerEventBus {
    int current;

    public ChangeViewPagerEventBus(int current) {
        this.current = current;
    }



    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
}
