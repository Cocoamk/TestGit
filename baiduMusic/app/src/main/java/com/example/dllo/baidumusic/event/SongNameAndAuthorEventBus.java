package com.example.dllo.baidumusic.event;

import java.util.List;

/**
 * Created by dllo on 16/6/13.
 */
public class SongNameAndAuthorEventBus {
    String author,title;

    public SongNameAndAuthorEventBus(String author, String title) {
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
