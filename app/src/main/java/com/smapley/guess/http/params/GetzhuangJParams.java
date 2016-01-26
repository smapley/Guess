package com.smapley.guess.http.params;

import com.smapley.guess.utils.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 15/11/22.
 */
public class GetzhuangJParams extends RequestParams {

    public GetzhuangJParams(String user1) {
        super(MyData.URL_getZhuangJ);
        addBodyParameter("user1",user1);
    }

}
