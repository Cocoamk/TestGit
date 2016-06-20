package com.example.dllo.baidumusic.event;

import java.util.List;

/**
 * Created by dllo on 16/6/13.
 */
public class PostSongIdEventBus {
    List<String> songId;

    public PostSongIdEventBus(List<String> songId) {
        this.songId = songId;
    }

    public List<String> getSongId() {
        return songId;
    }

    public void setSongId(List<String> songId) {
        this.songId = songId;
    }
}
