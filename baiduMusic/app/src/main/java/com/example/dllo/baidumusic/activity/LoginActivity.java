package com.example.dllo.baidumusic.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dllo.baidumusic.R;
import com.example.dllo.baidumusic.base.BaseActivity;
import com.example.dllo.baidumusic.fragment.PersonalFragment;


/**
 * Created by dllo on 16/6/7.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btnLogin;
    private EditText etNum,etPassword;
    @Override
    protected int getLayout() {
        return R.layout.activity_login ;
    }

    @Override
    protected void initView() {
        btnLogin = (Button) findViewById(R.id.btn_login);
        etNum = (EditText) findViewById(R.id.et_num_email);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin.setOnClickListener(this);

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        //Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show();
//     Intent intent = new Intent(this, PersonalFragment.class);
//        startActivity(intent);
        finish();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        String  backVaule = data.getStringExtra("myt1"); //值给了backVaue
//        if(backVaule.length()!=11){
//            Toast.makeText(this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
//        }else{
//            etNum.setText(backVaule);
//        }
//        String  backVaule1 = data.getStringExtra("wjn2"); //值给了backVaule
//
//        if(backVaule1.length() > 20){
//            Toast.makeText(this, "密码太长，请重新输入", Toast.LENGTH_SHORT).show();
//        }else if(backVaule1.length() < 6){
//            Toast.makeText(this, "密码太短，请重新输入", Toast.LENGTH_SHORT).show();
//        }else{
//            etPassword.setText(backVaule1);
//        }
//    }
}
