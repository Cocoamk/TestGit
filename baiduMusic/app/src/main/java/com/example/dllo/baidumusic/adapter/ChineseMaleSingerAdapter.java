package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.ChineseMaleSingerBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/6/6.
 */
public class ChineseMaleSingerAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ChineseMaleSingerBean.ArtistBean> chineseMaleSingerBeen;

    public ChineseMaleSingerAdapter(Context context) {
        this.context = context;
    }

    public void setChineseMaleSingerBeen(ArrayList<ChineseMaleSingerBean.ArtistBean> chineseMaleSingerBeen) {
        this.chineseMaleSingerBeen = chineseMaleSingerBeen;
    }

    @Override
    public int getCount() {
        return chineseMaleSingerBeen !=null&&chineseMaleSingerBeen.size()>0?chineseMaleSingerBeen.size():0;
    }

    @Override
    public Object getItem(int position) {
        return chineseMaleSingerBeen==null?null:chineseMaleSingerBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.chinese_male_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(chineseMaleSingerBeen.get(position).getAvatar_small()).fit().into(holder.imageView);
       holder.textViewSingerName.setText(chineseMaleSingerBeen.get(position).getName());
        return convertView;
    }
    class ViewHolder {
        ImageView imageView;
        TextView textViewSingerName;
        public ViewHolder(View itemView){
            imageView = (ImageView) itemView.findViewById(R.id.img_chinese_male_pic);
            textViewSingerName = (TextView) itemView.findViewById(R.id.tv_chinese_male_name);
        }
    }

}
