package com.example.dllo.baidumusic.net;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by dllo on 16/5/24.
 */
public class VolleySingleton {
    private static VolleySingleton ourInstance = new VolleySingleton();
 private RequestQueue requestQueue;
private ImageLoader imageLoader;
    public static VolleySingleton getInstance() {
        return ourInstance;
    }

    private VolleySingleton() {
        requestQueue = Volley.newRequestQueue(MyApp.context);
       imageLoader = new ImageLoader(requestQueue,new MemoryCache());
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }
public ImageLoader getImageLoader(){
    return imageLoader;
}
}
