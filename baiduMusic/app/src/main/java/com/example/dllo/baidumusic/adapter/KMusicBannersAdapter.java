package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/5/25.
 */
public class KMusicBannersAdapter extends PagerAdapter {
    private List<ImageView> imageViews;

    public KMusicBannersAdapter(Context context) {

    }

    @Override
    public int getCount() {
        return imageViews == null ? 0:Integer.MAX_VALUE;

    }
public  void  setImageViews(int[] photos , Context context){
    imageViews = new ArrayList<>();
    for (int photo : photos){
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(photo);
        imageViews.add(imageView);
    }
    notifyDataSetChanged();
}
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        ImageView imageView = imageViews.get(position % imageViews.size());
       try {
        container.addView(imageView);

       }catch (IllegalStateException e){

           container.removeView(imageView);
           container.addView(imageView);
       }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (container.getChildAt(position % imageViews.size())==object){
            container.removeViewAt(position % imageViews.size());
        }
    }
}
