package com.smapley.guess.http.service;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.guess.db.Refresh;
import com.smapley.guess.db.RefreshService;
import com.smapley.guess.db.entity.UserBaseEntity;
import com.smapley.guess.db.modes.DynamicMode;
import com.smapley.guess.db.service.DynamicService;
import com.smapley.guess.http.callback.SimpleCallback;
import com.smapley.guess.http.params.BaseParams;
import com.smapley.guess.utils.MyData;

import org.xutils.x;

import java.util.List;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class DynamicListService {

    private final int SAVEDATA = 1;
    private long time;
    private Refresh refresh;
    private RefreshService refreshService;

    public DynamicListService() {
        refreshService = new RefreshService();
    }

    public void load(UserBaseEntity userBaseEntity,int pro_id) {
        time = System.currentTimeMillis();
        refresh = refreshService.findById(userBaseEntity.getUseId());
        BaseParams params = new BaseParams(MyData.URL_DynamicList, userBaseEntity);
        params.addBodyParameter("time", refresh.getDynamicList() + "");
        params.addBodyParameter("pro_id", pro_id + "");
        x.http().post(params, new SimpleCallback() {
            @Override
            public void Success(final String data) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<DynamicMode> dynamicModes = JSON.parseObject(data, new TypeReference<List<DynamicMode>>() {
                        });
                        if (dynamicModes != null && !dynamicModes.isEmpty()) {
                            for (DynamicMode dynamicMode : dynamicModes) {
                                DynamicService.save(dynamicMode);
                            }
                            mhandler.obtainMessage(SAVEDATA).sendToTarget();
                        }
                    }
                }).start();
            }
        });
    }

    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case SAVEDATA:
                    refresh.setDynamicList(time);
                    refreshService.saveOrUpdate(refresh);
                    onSucceed();
                    break;
            }
        }
    };

    public abstract void onSucceed();
}