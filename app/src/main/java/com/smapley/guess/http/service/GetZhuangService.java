package com.smapley.guess.http.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.guess.http.callback.SimpleCallback;
import com.smapley.guess.http.params.GetZhuangParams;
import com.smapley.guess.mode.GetZhuangMode;

import org.xutils.x;

import java.util.List;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class GetZhuangService {

    public void load() {
        x.http().post(new GetZhuangParams(), new SimpleCallback() {
            @Override
            public void Success(final String data) {
                List<GetZhuangMode> result= JSON.parseObject(data,new TypeReference<List<GetZhuangMode>>(){});
                Succ(result);
            }
        });
    }


    public abstract void Succ(List<GetZhuangMode> data);
}