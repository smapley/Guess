package com.smapley.guess.application;

import android.util.DisplayMetrics;

import com.smapley.guess.BuildConfig;
import com.smapley.guess.exception.BaseExceptionHandler;
import com.smapley.guess.exception.LocalFileHandler;

import org.xutils.x;

/**
 * Created by smapley on 15/10/22.
 */
public class LocalApplication extends BaseApplication {

    private static LocalApplication instance;


    //当前屏幕的高宽
    public int screenW = 0;
    public int screenH = 0;

    //单例模式中获取唯一的MyApplication实例
    public static LocalApplication getInstance() {
        if (instance == null) {
            instance = new LocalApplication();
        }

        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();



        //初始化xUtils
        x.Ext.init(this);
        x.Ext.setDebug(BuildConfig.DEBUG);

        instance = this;

        //得到屏幕的宽度和高度
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        screenW = displayMetrics.widthPixels;
        screenH = displayMetrics.heightPixels;
    }

    @Override
    public BaseExceptionHandler getDefaultUncaughtExceptionHandler() {
        return new LocalFileHandler(applicationContext);
    }
}
