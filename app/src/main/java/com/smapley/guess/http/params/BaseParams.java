package com.smapley.guess.http.params;

import com.smapley.guess.db.entity.UserBaseEntity;
import com.smapley.guess.utils.Code;

import org.xutils.http.RequestParams;

/**
 * Created by smapley on 15/11/22.
 */
public class BaseParams extends RequestParams {

    public BaseParams(String url, UserBaseEntity userBaseEntity) {
        super(url);
        if (userBaseEntity != null) {
            addHeader("cookie", "JSESSIONID=" + Code.doCode(userBaseEntity.getSkey()));
        }
    }

}
