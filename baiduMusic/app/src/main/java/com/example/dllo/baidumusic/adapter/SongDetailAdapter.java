package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.SongDetailBean;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/31.
 */
public class SongDetailAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SongDetailBean.ContentBean> songDetailBeen;
    public SongDetailAdapter(Context context) {
        this.context = context;
    }

    public void setSongDetailBeen(ArrayList<SongDetailBean.ContentBean> songDetailBeen) {
        this.songDetailBeen = songDetailBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return songDetailBeen != null && songDetailBeen.size() > 0 ? songDetailBeen.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return songDetailBeen == null ? null : songDetailBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.song_detail_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvSongName.setText(songDetailBeen.get(position).getTitle());

        viewHolder.tvSongAuthor.setText(songDetailBeen.get(position).getAuthor());
        return convertView;
    }

    class ViewHolder {

        TextView tvSongName, tvSongAuthor;

        public ViewHolder(View itemView) {

            tvSongName = (TextView) itemView.findViewById(R.id.tv_song_detail_name);
            tvSongAuthor = (TextView) itemView.findViewById(R.id.tv_song_detail_singer);
        }
    }


}
