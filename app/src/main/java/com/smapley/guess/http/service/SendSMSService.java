package com.smapley.guess.http.service;

import com.smapley.guess.http.callback.SimpleCallback;
import com.smapley.guess.http.params.SendSMSParams;

import org.xutils.x;


/**
 * Created by smapley on 15/12/18.
 */
public abstract class SendSMSService {



    public void load(SendSMSParams params) {
        x.http().post(params, new SimpleCallback() {
            @Override
            public void Success(String data) {
                Succ();
            }
        });
    }

    public abstract void Succ();
}