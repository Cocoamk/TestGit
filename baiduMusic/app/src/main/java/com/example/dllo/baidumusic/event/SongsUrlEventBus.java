package com.example.dllo.baidumusic.event;

import java.util.List;

/**
 * Created by dllo on 16/6/6.
 */
public class SongsUrlEventBus {
    List<String> songPlayUrls;

    public SongsUrlEventBus(List<String> songPlayUrls) {
        this.songPlayUrls = songPlayUrls;
    }

    public List<String> getSongPlayUrls() {
        return songPlayUrls;
    }

    public void setSongPlayUrls(List<String> songPlayUrls) {
        this.songPlayUrls = songPlayUrls;
    }
}
