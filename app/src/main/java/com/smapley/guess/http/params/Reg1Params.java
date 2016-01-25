package com.smapley.guess.http.params;

import com.smapley.guess.utils.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 16/1/25.
 */
public class Reg1Params extends RequestParams {

    public Reg1Params(String phone, String mi) {
        super(MyData.URL_reg1);
        addBodyParameter("phone", phone);
        addBodyParameter("mi", mi);
    }
}
