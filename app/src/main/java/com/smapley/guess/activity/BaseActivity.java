package com.smapley.guess.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.smapley.guess.application.LocalApplication;
import com.smapley.guess.utils.ActivityStack;

import org.xutils.x;

/**
 * Created by smapley on 15/10/22.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private boolean isCreate = false;
    protected SharedPreferences sp_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getInstance().addActivity(this);
        x.view().inject(this);
        sp_user = LocalApplication.getInstance().sp_user;

        isCreate = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isCreate) {
            isCreate = false;
            initParams();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        //Activity堆栈管理
        ActivityStack.getInstance().removeActivity(this);
        super.onDestroy();
    }

    /**
     * 参数设置
     */
    protected abstract void initParams();


    protected void showToast(int data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(String data) {
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }

}
