package com.smapley.guess.activity;

import android.content.Intent;

import com.smapley.guess.R;

import org.xutils.view.annotation.ContentView;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {



    @Override
    protected void initParams() {
        if (sp_user.getBoolean("islogin", false)) {
            //如果登陆 则加载界面
            initView();
        } else {
            //如果没有登陆 则跳转到登陆界面
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }
    }


    private void initView() {

    }


}
