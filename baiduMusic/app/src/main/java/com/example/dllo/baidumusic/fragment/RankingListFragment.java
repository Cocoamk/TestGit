package com.example.dllo.baidumusic.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.activity.RankingPlayActivity;
import com.example.dllo.baidumusic.activity.SongDetailActivity;
import com.example.dllo.baidumusic.adapter.RankingContentAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.bean.RankingListData;
import com.example.dllo.baidumusic.net.MyApp;
import com.example.dllo.baidumusic.net.NetListener;
import com.example.dllo.baidumusic.net.NetTool;
import com.google.gson.Gson;

import java.util.ArrayList;


/**
 * Created by dllo on 16/5/19.
 */
public class RankingListFragment extends BaseFragment  {
    private ListView listView;
    private RankingContentAdapter adapter;
    private ImageView imageView;
    private TextView tvName, tvTitle1, tvTitle2, tvTitle3;
    private ArrayList<RankingListData> datas;

    @Override
    public int setLayout() {
        return R.layout.fragment_rankinglist;
    }

    @Override
    public void initView(View view) {
        listView = (ListView) view.findViewById(R.id.ranking_listView);
        imageView = (ImageView) view.findViewById(R.id.img_ranking);
        tvName = (TextView) view.findViewById(R.id.tv_rankingName);
        tvTitle1 = (TextView) view.findViewById(R.id.tv_ranking_Title1);
        tvTitle2 = (TextView) view.findViewById(R.id.tv_ranking_Title2);
        tvTitle3 = (TextView) view.findViewById(R.id.tv_ranking_Title3);
        adapter = new RankingContentAdapter(getContext());

    }

    @Override
    protected void initData() {
        datas = new ArrayList<>();
        NetTool netTool = new NetTool();
        netTool.getRankingList(new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                RankingListData rankingListData = gson.fromJson(result, RankingListData.class);
                for (int i = 0; i < 12; i++) {

                    datas.add(rankingListData);
                }
                adapter.setRankingListData(datas);
                listView.setAdapter(adapter);
//                Log.d("RankingListFragment--->", result);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //给行布局设置点击事件是,行布局里不能有Button,会抢占焦点
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("RankingListFragment","---------------------"+ position);
              Intent intent = new Intent(context, RankingPlayActivity.class);
                intent.putExtra("type",datas.get(position).getContent().get(position).getType());
                startActivity(intent);
            }
        });
    }




}
