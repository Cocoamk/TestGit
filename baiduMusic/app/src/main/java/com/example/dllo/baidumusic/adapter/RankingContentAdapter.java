package com.example.dllo.baidumusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.bean.RankingListData;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/26.
 */
public class RankingContentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<RankingListData> rankingListData;

    public RankingContentAdapter(Context context) {
        this.context = context;
    }


    public void setRankingListData(ArrayList<RankingListData> rankingListData) {
        this.rankingListData = rankingListData;
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {

        return rankingListData != null && rankingListData.size() > 0 ? rankingListData.size() : 0;

    }

    @Override
    public Object getItem(int position) {
        return rankingListData == null ? null : rankingListData.get(position);
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.ranking_item_list, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context).load(rankingListData.get(position).getContent().get(position).getPic_s192()).fit().into(holder.imageView);
        holder.textViewName.setText(rankingListData.get(position).getContent().get(position).getName());
        holder.tvTitle1.setText(rankingListData.get(position).getContent().get(position).getContent().get(0).getTitle());
        holder.tvTitle2.setText(rankingListData.get(position).getContent().get(position).getContent().get(1).getTitle());
        holder.tvTitle3.setText(rankingListData.get(position).getContent().get(position).getContent().get(2).getTitle());
        return convertView;
    }

    class ViewHolder {
        ImageView imageView;
        TextView textViewName, tvTitle1, tvTitle2, tvTitle3;

        public ViewHolder(View itemView) {
            imageView = (ImageView) itemView.findViewById(R.id.img_ranking);
            textViewName = (TextView) itemView.findViewById(R.id.tv_rankingName);
            tvTitle1 = (TextView) itemView.findViewById(R.id.tv_ranking_Title1);
            tvTitle2 = (TextView) itemView.findViewById(R.id.tv_ranking_Title2);
            tvTitle3 = (TextView) itemView.findViewById(R.id.tv_ranking_Title3);
        }
    }
}
