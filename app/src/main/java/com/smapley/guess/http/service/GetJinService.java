package com.smapley.guess.http.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.guess.http.callback.SimpleCallback;
import com.smapley.guess.http.params.GetJinParams;
import com.smapley.guess.mode.GetZhuangJMode;

import org.xutils.x;

import java.util.List;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class GetJinService {

    public void load(GetJinParams params) {
        x.http().post(params, new SimpleCallback() {
            @Override
            public void Success(final String data) {
                Succ(data);
            }
        });
    }


    public abstract void Succ(String data);
}