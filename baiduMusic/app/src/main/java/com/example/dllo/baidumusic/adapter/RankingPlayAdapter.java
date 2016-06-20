package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.RankingPlayBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/1.
 */
public class RankingPlayAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<RankingPlayBean.SongListBean> rankingPlayBeen;

    public RankingPlayAdapter(Context context) {
        this.context = context;
    }

    public void setRankingPlayBeen(ArrayList<RankingPlayBean.SongListBean> rankingPlayBeen) {
        this.rankingPlayBeen = rankingPlayBeen;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return rankingPlayBeen != null && rankingPlayBeen.size() > 0 ? rankingPlayBeen.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return rankingPlayBeen == null ? null : rankingPlayBeen.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ranking_play_item, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(rankingPlayBeen.get(position).getPic_small()).fit().into(viewHolder.imageView);
        viewHolder.tvRankingPlayName.setText(rankingPlayBeen.get(position).getTitle());
        viewHolder.tvRankingPlayAuthor.setText(rankingPlayBeen.get(position).getAuthor());
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView tvRankingPlayName, tvRankingPlayAuthor;

        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.img_ranking_song_pic);
            tvRankingPlayName = (TextView) itemView.findViewById(R.id.tv_ranking_play_name);
            tvRankingPlayAuthor = (TextView) itemView.findViewById(R.id.tv_ranking_play_singer);
        }
    }

}
