package com.smapley.guess.http.params;

import com.smapley.guess.utils.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 15/11/22.
 */
public class GetJinParams extends RequestParams {

    public GetJinParams(String user1,String mi) {
        super(MyData.URL_getJin);
        addBodyParameter("user1",user1);
        addBodyParameter("mi",mi);
    }

}
