package com.smapley.guess.http.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.guess.http.callback.SimpleCallback;
import com.smapley.guess.http.params.AddZhuangParams;
import com.smapley.guess.mode.GaiJiangMode;

import org.xutils.x;

import java.util.List;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class AddZhuangService {

    public void load(AddZhuangParams params) {

        x.http().post(params,new SimpleCallback() {
            @Override
            public void Success(final String data) {
                Succ(data);
            }
        });
    }


    public abstract void Succ(String data);
}