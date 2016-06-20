package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.RankingPlayBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class KMusicAdapter extends RecyclerView.Adapter<KMusicAdapter.KMusicViewHolder> {
    private Context context;
    private ArrayList<RankingPlayBean.SongListBean> rankingPlayBeen;

    public KMusicAdapter(Context context) {
        this.context = context;
    }

    public void setRankingPlayBeen(ArrayList<RankingPlayBean.SongListBean> rankingPlayBeen) {
        this.rankingPlayBeen = rankingPlayBeen;
        notifyDataSetChanged();
    }

    @Override
    public KMusicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(context).inflate(R.layout.k_item_list,parent,false);
        KMusicViewHolder holder = new KMusicViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(KMusicViewHolder holder, int position) {
holder.tvSongName.setText(rankingPlayBeen.get(position).getTitle());
        Picasso.with(context).load(rankingPlayBeen.get(position).getPic_small()).fit().into(holder.imageSongPic);
    }

    @Override
    public int getItemCount() {
    return rankingPlayBeen != null && rankingPlayBeen.size() > 0 ? rankingPlayBeen.size() : 0;
    }

    class KMusicViewHolder extends RecyclerView.ViewHolder{
        TextView tvSongName;
        ImageView imageSongPic;
        public KMusicViewHolder(View itemView) {
            super(itemView);
            tvSongName = (TextView) itemView.findViewById(R.id.tv_k_songs);
            imageSongPic = (ImageView) itemView.findViewById(R.id.img_k_music_pic);
        }
    }
}
