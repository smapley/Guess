package com.smapley.guess.http.params;

import com.smapley.guess.utils.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class AddZhuangParams extends RequestParams {

    public AddZhuangParams(String biaoti, String type, String dan, String pei,String mima) {
        super(MyData.URL_addZhuang);
        addBodyParameter("biaoti", biaoti);
        addBodyParameter("type", type);
        addBodyParameter("dan", dan);
        addBodyParameter("pei", pei);
        addBodyParameter("mima", mima);
    }
}
