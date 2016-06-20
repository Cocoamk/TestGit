package com.example.dllo.baidumusic.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.adapter.ChineseMaleSingerAdapter;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.bean.ChineseMaleSingerBean;
import com.example.dllo.baidumusic.net.NetListener;
import com.example.dllo.baidumusic.net.NetTool;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by dllo on 16/6/6.
 */
public class ChineseMaleSingerActivity extends BaseActivity implements View.OnClickListener {
    private Button btnChineseMaleReturn;
    private ListView chineseMaleListView;
    private ChineseMaleSingerAdapter chineseMaleSingerAdapter;
    private ChineseMaleSingerBean chineseMaleSingerBean;
    private ArrayList<ChineseMaleSingerBean.ArtistBean> bean;
    @Override
    protected int getLayout() {
        return R.layout.activity_chinese_male_singer;
    }

    @Override
    protected void initView() {
        btnChineseMaleReturn = (Button) findViewById(R.id.btn_chinese_male_singer_return);
        chineseMaleListView = (ListView) findViewById(R.id.chinese_male_listView);
        chineseMaleSingerAdapter = new ChineseMaleSingerAdapter(this);
        btnChineseMaleReturn.setOnClickListener(this);


    }

    @Override
    protected void initData() {
        bean = new ArrayList<>();
        NetTool netTool = new NetTool();
        netTool.getChineseMale(new NetListener() {
            @Override
            public void onSuccessed(String result) {
                Gson gson = new Gson();
                chineseMaleSingerBean = gson.fromJson(result,ChineseMaleSingerBean.class);
                chineseMaleSingerAdapter.setChineseMaleSingerBeen(chineseMaleSingerBean.getArtist());
                chineseMaleListView.setAdapter(chineseMaleSingerAdapter);
            }

            @Override
            public void onFailed(VolleyError error) {

            }
        });
        chineseMaleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ChineseMaleSingerActivity.this,MaleSingerSongsActivity.class);
                intent.putExtra("aaa",chineseMaleSingerBean.getArtist().get(position).getTing_uid());
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_chinese_male_singer_return:
                finish();
                break;

        }
    }
}
