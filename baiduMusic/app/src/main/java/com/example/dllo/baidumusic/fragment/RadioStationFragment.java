package com.example.dllo.baidumusic.fragment;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseFragment;


/**
 * Created by dllo on 16/5/19.
 */
public class RadioStationFragment extends BaseFragment implements View.OnClickListener {
    private LinearLayout llHeartChannel,llPersonalChannel;
    @Override
    public int setLayout() {
        return R.layout.fragment_radiostation;
    }

    @Override
    public void initView(View view) {
llHeartChannel = (LinearLayout) view.findViewById(R.id.ll_heartChannel);
        llPersonalChannel = (LinearLayout) view.findViewById(R.id.ll_personalChannel);
        llPersonalChannel.setOnClickListener(this);
        llHeartChannel.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_heartChannel:
         showAlertDialog();
                break;
            case R.id.ll_personalChannel:
               showAlertDialogP();
                break;
        }
    }
//对话框
public  void showAlertDialog() {
    AlertDialog.Builder alert1 = new AlertDialog.Builder(context);

    //设置标题
    alert1.setTitle("红心频道将在登录后开启,您可以收听所有点过红心的歌曲");


    alert1.setPositiveButton("立即登录", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
    });
    //positionButton 通常放 取消 不行
    //参数:点击的监听
    alert1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
    });
    alert1.show();
}
    public  void showAlertDialogP() {
    AlertDialog.Builder alert2 = new AlertDialog.Builder(context);

    //设置标题
    alert2.setTitle("私人频道已准备好,请登录后享用");

    alert2.setPositiveButton("立即登录", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
    });
    //positionButton 通常放 取消 不行
    //参数:点击的监听
    alert2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
        }
    });
    alert2.show();
}
}
