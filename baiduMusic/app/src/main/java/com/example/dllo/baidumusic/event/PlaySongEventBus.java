package com.example.dllo.baidumusic.event;

/**
 * Created by dllo on 16/6/1.
 */
public class PlaySongEventBus {
    String songId;

    public PlaySongEventBus( String songId) {
        this.songId = songId;

    }


    public String getSongId() {
        return songId;
    }

    public void setSongId(String songId) {
        this.songId = songId;
    }


}
