package com.smapley.powerwork.http.service;

import android.os.Handler;
import android.os.Message;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.powerwork.db.Refresh;
import com.smapley.powerwork.db.RefreshService;
import com.smapley.powerwork.db.entity.UserBaseEntity;
import com.smapley.powerwork.db.modes.FolderMode;
import com.smapley.powerwork.db.service.FolderService;
import com.smapley.powerwork.http.callback.SimpleCallback;
import com.smapley.powerwork.http.params.BaseParams;
import com.smapley.powerwork.utils.MyData;

import org.xutils.x;

/**
 * Created by smapley on 15/12/18.
 */
public abstract class FolderListService {

    private final int SAVEDATA = 1;
    private long time;
    private Refresh refresh;
    private RefreshService refreshService;

    public FolderListService() {
        refreshService = new RefreshService();
    }

    public void load(UserBaseEntity userBaseEntity,int pro_id) {
        time = System.currentTimeMillis();
        refresh = refreshService.findById(userBaseEntity.getUseId());
        BaseParams params = new BaseParams(MyData.URL_FolderList, userBaseEntity);
        params.addBodyParameter("time", refresh.getFolderList() + "");
        params.addBodyParameter("pro_id", pro_id + "");
        x.http().post(params, new SimpleCallback() {
            @Override
            public void onSuccess(final String data) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        FolderMode folderMode = JSON.parseObject(data, new TypeReference<FolderMode>() {
                        });
                        if (folderMode != null) {
                            FolderService.save(folderMode);
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
                    refresh.setFolderList(time);
                    refreshService.saveOrUpdate(refresh);
                    onSucceed();
                    break;
            }
        }
    };

    public abstract void onSucceed();
}