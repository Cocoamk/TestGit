package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.SongListBean;
import com.example.dllo.baidumusic.tools.SongListRVOnClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/20.
 */
public class SongsListAdapter extends RecyclerView.Adapter<SongsListAdapter.SongViewHolder> {
    private Context context;
    private ArrayList<SongListBean> data;
    private SongListRVOnClickListener songListRVOnClickListener;

    public void setSongListRVOnClickListener(SongListRVOnClickListener songListRVOnClickListener) {
        this.songListRVOnClickListener = songListRVOnClickListener;
    }

    public SongsListAdapter(Context context) {
        this.context = context;
    }


    public void setData(ArrayList<SongListBean> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @Override
    public SongViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.songlist_item, parent, false);
        SongViewHolder holder = new SongViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(final SongViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getContent().get(position).getPic_w300()).fit().into(holder.imageView);
        holder.textViewTitle.setText(data.get(position).getContent().get(position).getTitle());
        holder.textViewTag.setText(data.get(position).getContent().get(position).getTag());
       if (songListRVOnClickListener!=null){
           holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = holder.getLayoutPosition();
                   songListRVOnClickListener.onClick(position);
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        return data != null && data.size() > 0 ? data.size() : 0;
    }

    class SongViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle,textViewTag;
        ImageView imageView;
      RelativeLayout relativeLayout;
        public SongViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.tv_songList_title);
            textViewTag = (TextView) itemView.findViewById(R.id.tv_songList_tag);
            imageView = (ImageView) itemView.findViewById(R.id.img_songList);
           relativeLayout = (RelativeLayout) itemView.findViewById(R.id.rl_rankingList);
        }
    }
}
