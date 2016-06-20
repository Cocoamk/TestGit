package com.example.dllo.baidumusic.net;

/**
 * Created by dllo on 16/5/24.
 */
public final class URLValues {
    public static final String RANKING_LIST = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billCategory&format=json&from=ios&version=5.2.1&from=ios&channel=appstore";
    public static final String SONGLIST = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedan&page_no=1&page_size=30&from=ios&version=5.2.3&from=ios&channel=appstore";
    public static final String MUSIC_RECOMMEND = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.getHotGeDanAndOfficial&num=4&version=5.2.3&from=ios&channel=appstore";
    public static final String SONG_DETAIL = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
    public static final String SONG_DETAIL_END = "&version=5.2.3&from=ios&channel=appstore";
    public static final String RANKING_PLAY = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&format=json&offset=0&size=50&from=ios&fields=title,song_id,author,resource_type,havehigh,is_new,has_mv_mobile,album_title,ting_uid,album_id,charge,all_rate&version=5.2.1&from=ios&channel=appstore";
    public static final String SONG_PLAY = "http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    public static final String SONG_PLAY_END = "&_=1413017198449";
   public static final String CHINESE_MALE ="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.artist.getList&format=json&order=1&limit=50&offset=0&area=6&sex=1&abc=%E7%83%AD%E9%97%A8&from=ios&version=5.2.5&from=ios&channel=appstore";
}
