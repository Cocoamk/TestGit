package com.example.dllo.baidumusic.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.activity.LoginActivity;
import com.example.dllo.baidumusic.base.BaseFragment;


/**
 * Created by dllo on 16/5/20.
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {
    private Button btnLogIn,btnMoreReturn;

    @Override
    public int setLayout() {
        return R.layout.fragment_personal;
    }

    @Override
    public void initView(View view) {
btnLogIn = (Button) view.findViewById(R.id.btn_login);
        btnMoreReturn = (Button) view.findViewById(R.id.btn_more_return);
        btnMoreReturn.setOnClickListener(this);
        btnLogIn.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
        Intent intent = new Intent(context, LoginActivity.class);
        startActivity(intent);
                break;
            case R.id.btn_more_return:

                break;
        }

    }
    //Fragment之间跳转的方法
    private void fragmentReplace(int id,Fragment fragment) {
        FragmentManager m = getActivity().getSupportFragmentManager();
        FragmentTransaction fm = m.beginTransaction();
        fm.replace(id,fragment).addToBackStack(null);//加到返回栈
        fm.commit();
    }
}
