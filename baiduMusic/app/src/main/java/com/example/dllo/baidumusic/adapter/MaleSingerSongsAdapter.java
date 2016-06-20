package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.MaleSingerSongsBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/6.
 */
public class MaleSingerSongsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MaleSingerSongsBean.SonglistBean> maleSingerSongsBeen;

    public MaleSingerSongsAdapter(Context context) {
        this.context = context;
    }

    public void setMaleSingerSongsBeen(ArrayList<MaleSingerSongsBean.SonglistBean> maleSingerSongsBeen) {
        this.maleSingerSongsBeen = maleSingerSongsBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return maleSingerSongsBeen !=null && maleSingerSongsBeen.size()>0?maleSingerSongsBeen.size():0;
    }

    @Override
    public Object getItem(int position) {
        return maleSingerSongsBeen==null?null:maleSingerSongsBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.song_detail_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.textViewSingerName.setText(maleSingerSongsBeen.get(position).getAuthor());
        holder.tvSongsName.setText(maleSingerSongsBeen.get(position).getTitle());
        return convertView;
    }
    class ViewHolder {

        TextView textViewSingerName,tvSongsName;
        public ViewHolder(View itemView){
            tvSongsName = (TextView) itemView.findViewById(R.id.tv_song_detail_name);
            textViewSingerName= (TextView) itemView.findViewById(R.id.tv_song_detail_singer);
        }
    }
    }

