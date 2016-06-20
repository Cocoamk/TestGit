package com.example.dllo.baidumusic.fragment;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.activity.SongDetailActivity;
import com.example.dllo.baidumusic.adapter.SongsListAdapter;
import com.example.dllo.baidumusic.base.BaseFragment;
import com.example.dllo.baidumusic.bean.SongListBean;
import com.example.dllo.baidumusic.net.NetListener;
import com.example.dllo.baidumusic.net.NetTool;
import com.example.dllo.baidumusic.tools.SongListRVOnClickListener;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/5/19.
 */
public class SongListFragment extends BaseFragment  {
    private RecyclerView recyclerView;
    private SongsListAdapter adapter;
    private ArrayList<SongListBean> datas;
    private ImageView imageView;
    private TextView textViewTitle,textViewTag;

    @Override
    public int setLayout() {
        return R.layout.fragment_songlist;
    }

    @Override
    public void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.songs_list_recyclerView);
        imageView = (ImageView) view.findViewById(R.id.img_songList);
        textViewTag = (TextView) view.findViewById(R.id.tv_songList_title);
        textViewTag = (TextView) view.findViewById(R.id.tv_songList_tag);
        adapter = new SongsListAdapter(getContext());
        datas = new ArrayList<>();
        adapter.setData(datas);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 2);
        manager.setOrientation(GridLayout.VERTICAL);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
    }

    @Override
    protected void initData() {

        NetTool netTool = new NetTool();
        netTool.getSongList(new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                SongListBean data = gson.fromJson(result,SongListBean.class);
                for (int i = 0; i < 30; i++) {
                   datas.add(data);
                }
                adapter.setData(datas);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        //RecyclerView的点击事件
       adapter.setSongListRVOnClickListener(new SongListRVOnClickListener() {
    @Override
    public void onClick(int position) {
        Intent intent = new Intent(context, SongDetailActivity.class);
        intent.putExtra("list",datas.get(position).getContent().get(position).getListid());
                startActivity(intent);
    }
});
    }


}
