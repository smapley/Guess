package com.smapley.guess.http.params;

import com.smapley.guess.utils.MyData;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 15/11/22.
 */
public class UpdateZt1Params extends RequestParams {

    public UpdateZt1Params(String allid) {
        super(MyData.URL_updateZt1);
        addBodyParameter("allid",allid);
    }

}
