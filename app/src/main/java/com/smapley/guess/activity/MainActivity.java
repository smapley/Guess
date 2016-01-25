package com.smapley.guess.activity;

import android.content.Intent;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.smapley.guess.R;
import com.smapley.guess.fragment.BaseFragment;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
