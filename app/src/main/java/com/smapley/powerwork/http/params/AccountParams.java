package com.smapley.powerwork.http.params;

import com.smapley.powerwork.db.entity.UserBaseEntity;
import com.smapley.powerwork.utils.MyData;

/**
 * Created by smapley on 15/12/18.
 */
public class AccountParams extends BaseParams {


    public AccountParams(UserBaseEntity userBaseEntity) {
        super(MyData.URL_Account, userBaseEntity);
    }

    public AccountParams setTruename(String truename){
        addBodyParameter("truename", truename);
        return this;
    }

    public AccountParams setPhone(String phone){
        addBodyParameter("phone", phone);
        return this;
    }
    public AccountParams setBirthday(Long birthday){
        addBodyParameter("birthday", birthday + "");
        return this;
    }


}