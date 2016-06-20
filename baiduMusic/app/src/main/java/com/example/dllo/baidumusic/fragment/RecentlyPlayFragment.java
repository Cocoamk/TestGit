package com.example.dllo.baidumusic.fragment;

import android.view.View;
import android.widget.Button;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;

/**
 * Created by dllo on 16/5/21.
 */
public class RecentlyPlayFragment extends BaseFragment {
    private Button button;

    @Override
    public int setLayout() {
        return R.layout.fragment_recently_play;
    }

    @Override
    public void initView(View view) {
        button = (Button) view.findViewById(R.id.btn_recently_play_finish);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }

        });
    }

    @Override
    protected void initData() {

    }
}
