package com.example.dllo.baidumusic.event;

import java.util.List;

/**
 * Created by dllo on 16/6/13.
 */
public class PostAllSongNameEventBus {
    List<String> Title;

    public PostAllSongNameEventBus(List<String> title) {
        Title = title;
    }

    public List<String> getTitle() {
        return Title;
    }

    public void setTitle(List<String> title) {
        Title = title;
    }
}
