package com.smapley.guess.fragment;

import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.smapley.guess.R;
import com.smapley.guess.adapter.ProItem1Adapter;
import com.smapley.guess.db.entity.DynamicEntity;
import com.smapley.guess.db.entity.FileEntity;
import com.smapley.guess.db.entity.ProjectEntity;
import com.smapley.guess.db.entity.TaskEntity;
import com.smapley.guess.db.entity.UserEntity;
import com.smapley.guess.http.service.DynamicListService;
import com.smapley.guess.mode.Pro_Item1_Mode;

import org.xutils.ex.DbException;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smapley on 15/11/16.
 */
@ContentView(R.layout.fragment_pro_item1)
public class Pro_Item1 extends BaseFragment {


    private static final int GETDATE = 1;
    private static final int SAVEDATA = 2;
    @ViewInject(R.id.pro_item1_rv_list)
    private RecyclerView pro_item1_rv_list;

    private List<Pro_Item1_Mode> list;
    private ProItem1Adapter adapter;
    private int pro_id;


    private DynamicListService dynamicListService = new DynamicListService() {
        @Override
        public void onSucceed() {
            getDataForDb();
        }
    };

    @Override
    protected void initParams(View view) {
        initData();
        initView();
        getDataForDb();
        getDataForWeb();


    }


    private void initData() {
        pro_id = getArguments().getInt("pro_id");
    }

    private void initView() {
        pro_item1_rv_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new ProItem1Adapter(getActivity(), list);
        pro_item1_rv_list.setAdapter(adapter);
    }

    public void getDataForDb() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<DynamicEntity> listDynamic = dbUtils
                            .selector(DynamicEntity.class)
                            .where("pro_id", "=", pro_id + "")
                            .orderBy("cre_date", true)
                            .findAll();
                    if (listDynamic != null && !listDynamic.isEmpty()) {
                        list.clear();
                        for (DynamicEntity dynamicEntity : listDynamic) {
                            Pro_Item1_Mode mode=new Pro_Item1_Mode();
                            mode.setDynamicEntity(dynamicEntity);
                            mode.setUserEntity(dbUtils.findById(UserEntity.class, dynamicEntity.getUse_id()));
                            mode.setProjectEntity(dbUtils.findById(ProjectEntity.class,dynamicEntity.getPro_id()));
                            switch (dynamicEntity.getType()) {
                                case 0:
                                    //创建项目
                                    list.add(mode);
                                    break;
                                case 1:
                                    //创建了任务
                                    mode.setTaskEntity(dbUtils.findById(TaskEntity.class,dynamicEntity.getTas_id()));
                                    list.add(mode);
                                    break;
                                case 2:
                                    //上传了文件
                                    mode.setFileEntity(dbUtils.findById(FileEntity.class,dynamicEntity.getFil_id()));
                                    list.add(mode);
                                    break;
                            }
                        }
                        mhandler.obtainMessage(GETDATE).sendToTarget();
                    }
                } catch (DbException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void getDataForWeb() {
        dynamicListService.load(userBaseEntity, pro_id);
    }


    private Handler mhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GETDATE:
                    adapter.notifyDataSetChanged();
                    break;
                case SAVEDATA:
                    getDataForDb();
                    break;
            }
        }
    };

}
