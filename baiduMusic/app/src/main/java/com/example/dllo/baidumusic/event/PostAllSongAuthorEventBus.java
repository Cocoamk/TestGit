package com.example.dllo.baidumusic.event;

import java.util.List;

/**
 * Created by dllo on 16/6/13.
 */
public class PostAllSongAuthorEventBus {
   List<String> author;

    public PostAllSongAuthorEventBus(List<String> author) {
        this.author = author;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }
}
