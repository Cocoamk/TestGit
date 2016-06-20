package com.example.dllo.baidumusic.net;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by dllo on 16/5/24.
 */
public class NetTool {
    private RequestQueue requestQueue;
private String listId;
    public NetTool() {
        requestQueue = VolleySingleton.getInstance().getRequestQueue();


    }

    public void getRankingList(final NetListener netListener) {
        StringRequest request = new StringRequest(URLValues.RANKING_LIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        });
        requestQueue.add(request);
    }

    public void getSongList(final NetListener netListener) {
        StringRequest request = new StringRequest(URLValues.SONGLIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        });
        requestQueue.add(request);
    }
    public void getMusicRecommend(final NetListener netListener) {
        StringRequest request = new StringRequest(URLValues.SONGLIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        });
        requestQueue.add(request);
    }
    public void getChineseMale(final NetListener netListener) {

        StringRequest request = new StringRequest(URLValues.CHINESE_MALE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        });
        requestQueue.add(request);
    }
    public void getRankingPlay(final NetListener netListener) {
        StringRequest request = new StringRequest(URLValues.RANKING_PLAY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                netListener.onSuccessed(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                netListener.onFailed(error);
            }
        });
        requestQueue.add(request);
    }

}