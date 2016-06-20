package com.example.dllo.baidumusic.activity;

import android.content.Intent;
import android.os.CountDownTimer;
import android.widget.ImageView;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseActivity;

/**
 * Created by dllo on 16/5/19.
 */
public class WelcomeActivity extends BaseActivity {
    private ImageView imageView;
    private CountDownTimer countDownTimer;
    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;

    }

    @Override
    protected void initView() {
        imageView = (ImageView) findViewById(R.id.img_welcome);
countDownTimer = new CountDownTimer(3000,1000) {
    @Override
    public void onTick(long millisUntilFinished) {

    }

    @Override
    public void onFinish() {
        Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}.start();
    }

    @Override
    protected void initData() {

    }
}
