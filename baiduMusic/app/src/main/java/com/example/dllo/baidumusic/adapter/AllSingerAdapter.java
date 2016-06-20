package com.example.dllo.baidumusic.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.volley.toolbox.ImageLoader;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.net.MyApp;
import com.example.dllo.baidumusic.net.VolleySingleton;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/4.
 */
public class AllSingerAdapter extends PagerAdapter {
    private ArrayList<String> bannerUrls;

    public void setBannerUrls(ArrayList<String> bannerUrls) {
        this.bannerUrls = bannerUrls;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        //设置无上限的数量,可以无限的滑动
        return bannerUrls != null ? Integer.MAX_VALUE : 0;


    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        //取出指定位置的ImageView
        ImageView imageView = new ImageView(MyApp.context);

        ImageLoader imageLoader = VolleySingleton.getInstance().getImageLoader();
        imageLoader.get(bannerUrls.get(position % bannerUrls.size()), ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher));
        //当图片少的时候,不会触发destroyItem
        //这个时候去向container中addView会报错
        //手动捕获异常
        try {

            container.addView(imageView);
        } catch (IllegalStateException e) {
            //从container中移除ImageView
            container.removeView(imageView);
            //再次添加
            container.addView(imageView);
        }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position % bannerUrls.size()) == object) {
            //销毁指定位置的ImageView回收内存
            container.removeViewAt(position % bannerUrls.size());
        }
    }
}