package com.example.dllo.baidumusic.net;

import com.android.volley.VolleyError;

/**
 * Created by dllo on 16/5/24.
 */
public interface NetListener {
    void onSuccessed(String result);
    void onFailed(VolleyError error);
}
