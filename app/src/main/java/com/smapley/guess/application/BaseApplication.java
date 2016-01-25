package com.smapley.guess.application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.smapley.guess.exception.BaseExceptionHandler;
import com.smapley.guess.utils.MyData;

/**
 * Created by smapley on 15/10/22.
 */
public abstract class BaseApplication extends Application {
    public static final String TAG = "Application";
    public static Context applicationContext;

    //以键值对的形式存储用户名和密码
    public SharedPreferences sp_user;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        //异常拦截
//        if(getDefaultUncaughtExceptionHandler()==null){
//            Thread.setDefaultUncaughtExceptionHandler(new LocalFileHandler(applicationContext));
//        }else{
//            Thread.setDefaultUncaughtExceptionHandler(getDefaultUncaughtExceptionHandler());
//        }

        //初始化键值对存储
        sp_user = getSharedPreferences(MyData.SP_USER, MODE_PRIVATE);
    }

    public abstract BaseExceptionHandler getDefaultUncaughtExceptionHandler();

}
